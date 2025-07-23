package com.portal.supplierportal.service.generator;

import com.portal.supplierportal.dto.PontoDTO;
import com.portal.supplierportal.dto.RelatorioPontoDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ExcelGenerator {

    public ByteArrayInputStream gerarRelatorioExcel(RelatorioPontoDTO relatorio, String caminhoArquivo) throws IOException {
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
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private CellStyle criarEstiloNegrito(Workbook workbook) {
        Font fonteNegrito = workbook.createFont();
        fonteNegrito.setBold(true);

        CellStyle estilo = workbook.createCellStyle();
        estilo.setFont(fonteNegrito);
        return estilo;
    }

    private void criarCabecalho(Sheet sheet, CellStyle estiloNegrito, RelatorioPontoDTO dados) {
        Row headerRow = sheet.createRow(0);
        Cell cell0 = headerRow.createCell(0);
        cell0.setCellValue("Item Analisado");
        cell0.setCellStyle(estiloNegrito);

        List<String> headersFixos = Arrays.asList("Item Analisado", "Resultado", "Município/UF", "Área (ha)", "Bioma", "Faixa de fronteira", "Módulo Fiscal", "Situação");
        for (int i = 0; i < headersFixos.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headersFixos.get(i));
            cell.setCellStyle(estiloNegrito);
        }
    }

    private void ajustarTamanhoColunas(Sheet sheet, int colunas) {
        for (int i = 0; i < colunas; i++) {
            sheet.autoSizeColumn(i);
        }
    }

}