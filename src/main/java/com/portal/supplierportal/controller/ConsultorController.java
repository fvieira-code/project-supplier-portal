package com.portal.supplierportal.controller;

import com.portal.supplierportal.dto.ConsultorDTO;
import com.portal.supplierportal.service.ConsultorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultores")
@RequiredArgsConstructor
public class ConsultorController {

    private final ConsultorService consultorService;

    @GetMapping
    public ResponseEntity<List<ConsultorDTO>> listarTodos() {
        return ResponseEntity.ok(consultorService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<ConsultorDTO> salvar(@Valid @RequestBody ConsultorDTO dto) {
        return ResponseEntity.ok(consultorService.salvar(dto));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ConsultorDTO> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(consultorService.buscarPorCpf(cpf));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultorDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody ConsultorDTO dto) {
        return ResponseEntity.ok(consultorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        consultorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
