package com.portal.supplierportal.service;

import com.portal.supplierportal.dto.PontoDTO;
import com.portal.supplierportal.dto.RelatorioPontoDTO;
import com.portal.supplierportal.mapper.PontoMapper;
import com.portal.supplierportal.repository.*;
import com.portal.supplierportal.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PontoService {

    private final PontoRepository pontoRepository;
    private final ConsultorRepository consultorRepository;
    private final ClienteRepository clienteRepository;

    public PontoDTO salvar(PontoDTO dto) {
        Consultor consultor = consultorRepository.findById(dto.getIdConsultor())
                .orElseThrow(() -> new RuntimeException("Consultor não encontrado"));
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Ponto ponto = PontoMapper.toEntity(dto, consultor, cliente);
        return PontoMapper.toDTO(pontoRepository.save(ponto));
    }

    public List<PontoDTO> listarTodos() {
        return pontoRepository.findAll()
                .stream().map(PontoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Page<PontoDTO> listarPaginado(Pageable pageable) {
        return pontoRepository.findAll(pageable).map(PontoMapper::toDTO);
    }

    public PontoDTO atualizar(Integer id, PontoDTO dto) {
        var existente = pontoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ponto não encontrado"));

        Consultor consultor = consultorRepository.findById(dto.getIdConsultor())
                .orElseThrow(() -> new RuntimeException("Consultor não encontrado"));
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Ponto atualizado = PontoMapper.toEntity(dto, consultor, cliente);
        atualizado.setId(id);

        return PontoMapper.toDTO(pontoRepository.save(atualizado));
    }

    public void deletar(Integer id) {
        pontoRepository.deleteById(id);
    }

    public RelatorioPontoDTO buscarPorFiltroComTotal(LocalDate dataInicial, LocalDate dataFinal,
                                                     Integer idConsultor, Integer idCliente) {
        List<Ponto> pontos = pontoRepository.findByFiltro(dataInicial, dataFinal, idConsultor, idCliente);
        List<PontoDTO> dtos = pontos.stream().map(PontoMapper::toDTO).toList();

        // soma total de horas
        Duration total = pontos.stream()
                .filter(p -> p.getTotal() != null)
                .map(Ponto::getTotal)
                .map(t -> Duration.ofHours(t.getHour()).plusMinutes(t.getMinute()))
                .reduce(Duration.ZERO, Duration::plus);

        long hours = total.toHours();
        long minutes = total.minusHours(hours).toMinutes();
        String totalHoras = String.format("%02d:%02d", hours, minutes);

        return new RelatorioPontoDTO(dtos, totalHoras);
    }

    public byte[] gerarRelatorioExcel(LocalDate dataInicial, LocalDate dataFinal,
                                      Integer idConsultor, Integer idCliente) throws IOException {
        var relatorio = buscarPorFiltroComTotal(dataInicial, dataFinal, idConsultor, idCliente);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Relatório de Pontos");

            // Cabeçalho
            Row header = sheet.createRow(0);
            String[] colunas = {
                    "ID", "Data", "Dia", "Início", "Fim", "Total", "Atividade", "Status",
                    "Ticket", "Consultor", "Cliente"
            };
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }

            // Dados
            int rowIdx = 1;
            for (PontoDTO dto : relatorio.getRegistros()) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(dto.getId());
                row.createCell(1).setCellValue(dto.getData().toString());
                row.createCell(2).setCellValue(dto.getDia());
                row.createCell(3).setCellValue(dto.getInicio() != null ? dto.getInicio().toString() : "");
                row.createCell(4).setCellValue(dto.getFim() != null ? dto.getFim().toString() : "");
                row.createCell(5).setCellValue(dto.getTotal() != null ? dto.getTotal().toString() : "");
                row.createCell(6).setCellValue(dto.getAtividade());
                row.createCell(7).setCellValue(dto.getStatus());
                row.createCell(8).setCellValue(dto.getTicket());
                row.createCell(9).setCellValue(dto.getIdConsultor().toString());
                row.createCell(10).setCellValue(dto.getIdCliente().toString());
            }

            // Total
            Row totalRow = sheet.createRow(rowIdx + 1);
            totalRow.createCell(0).setCellValue("Total de Horas:");
            totalRow.createCell(1).setCellValue(relatorio.getTotalHoras());

            // Auto size
            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        }
    }

}
