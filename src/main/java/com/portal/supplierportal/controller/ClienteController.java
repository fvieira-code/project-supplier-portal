package com.portal.supplierportal.controller;

import com.portal.supplierportal.dto.ClienteDTO;
import com.portal.supplierportal.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(@Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<ClienteDTO> buscarPorCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(clienteService.buscarPorCnpj(cnpj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pagina")
    public ResponseEntity<Page<ClienteDTO>> listarPaginado(Pageable pageable) {
        return ResponseEntity.ok(clienteService.listarPaginado(pageable));
    }
}
