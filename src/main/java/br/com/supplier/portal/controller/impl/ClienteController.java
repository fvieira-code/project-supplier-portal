package br.com.supplier.portal.controller.impl;

import br.com.supplier.portal.controller.ClienteControllerApi;
import br.com.supplier.portal.controller.ConsultorControllerApi;
import br.com.supplier.portal.model.entity.AtividadeEntity;
import br.com.supplier.portal.model.entity.ClienteEntity;
import br.com.supplier.portal.model.entity.ConsultorEntity;
import br.com.supplier.portal.repository.ClienteRepository;
import br.com.supplier.portal.repository.ConsultorRepository;
import br.com.supplier.portal.service.ClienteService;
import br.com.supplier.portal.service.ConsultorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Cliente", description = "Cliente Controller.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ClienteController implements ClienteControllerApi {

    @Autowired
    private ClienteService clienteService;

    @Override
    public ResponseEntity<ClienteEntity> salvar(@RequestBody ClienteEntity cliente) {

        ClienteEntity consultorResponse = clienteService.salvar(cliente);
        return new ResponseEntity<>(consultorResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClienteEntity> atualizar(@PathVariable("id") Integer id, @RequestBody ClienteEntity cliente) {

        ClienteEntity updateCliente = clienteService.listarPorId(id);
        if (updateCliente != null) {
            cliente.setIdCliente(updateCliente.getIdCliente());
            return new ResponseEntity<>(clienteService.atualizar(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ClienteEntity> excluir(@PathVariable("id") Integer id, @RequestBody ClienteEntity cliente) {

        ClienteEntity updateCliente = clienteService.listarPorId(id);

        if (updateCliente != null) {
            cliente.setIdCliente(updateCliente.getIdCliente());
            return new ResponseEntity<>(clienteService.excluir(updateCliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ClienteEntity>> listar() {

        return new ResponseEntity<>(clienteService.listar(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<ClienteEntity>> listar(Pageable cliente) {

        Page<ClienteEntity> consultorEntityPage = clienteService.listar(cliente);
        return ResponseEntity.ok().body(consultorEntityPage);
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarClientesPage() {

        return new ResponseEntity<>(clienteService.listarClienteesPage(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClienteEntity> listarPorId(@PathVariable("id") Integer id) {
        var cliente = clienteService.listarPorId(id);

        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
