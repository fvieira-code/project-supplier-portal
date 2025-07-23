package com.portal.supplierportal.controller;

import com.portal.supplierportal.dto.PontoDTO;
import com.portal.supplierportal.dto.RelatorioPontoDTO;
import com.portal.supplierportal.service.PontoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pontos")
@RequiredArgsConstructor
public class PontoController {

    private final PontoService pontoService;

    @PostMapping
    public ResponseEntity<PontoDTO> salvar(@Valid @RequestBody PontoDTO dto) {
        return ResponseEntity.ok(pontoService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<PontoDTO>> listarTodos() {
        return ResponseEntity.ok(pontoService.listarTodos());
    }

    @GetMapping("/pagina")
    public ResponseEntity<Page<PontoDTO>> listarPaginado(Pageable pageable) {
        return ResponseEntity.ok(pontoService.listarPaginado(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PontoDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody PontoDTO dto) {
        return ResponseEntity.ok(pontoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        pontoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtro")
    public ResponseEntity<RelatorioPontoDTO> buscarPorFiltro(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestParam(required = false) Integer idConsultor,
            @RequestParam(required = false) Integer idCliente
    ) {
        return ResponseEntity.ok(pontoService.buscarPorFiltroComTotal(dataInicial, dataFinal, idConsultor, idCliente));
    }

    @GetMapping("/filtro/excel")
    public ResponseEntity<byte[]> gerarExcelPorFiltro(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestParam(required = false) Integer idConsultor,
            @RequestParam(required = false) Integer idCliente
    ) throws IOException {
        byte[] bytes = pontoService.gerarRelatorioExcel(dataInicial, dataFinal, idConsultor, idCliente);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-pontos.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
    }

}
