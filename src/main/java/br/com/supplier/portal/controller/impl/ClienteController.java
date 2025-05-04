package br.com.supplier.portal.controller.impl;

import br.com.supplier.portal.controller.ClienteControllerApi;
import br.com.supplier.portal.controller.ConsultorControllerApi;
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
    private ClienteService consultorService;

    @Autowired
    private ClienteRepository consultorRepository;

    @Override
    public ResponseEntity<ClienteEntity> salvar(@RequestBody ClienteEntity cliente) {

        ClienteEntity consultorResponse = consultorService.salvar(cliente);
        return new ResponseEntity<>(consultorResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClienteEntity> atualizar(@PathVariable("id") Integer id, @RequestBody ClienteEntity cliente) {

        ClienteEntity updateCliente = consultorService.listarPorId(id);
        if (updateCliente != null) {
            cliente.setIdCliente(updateCliente.getIdCliente());
            return new ResponseEntity<>(consultorService.atualizar(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ClienteEntity> excluir(@PathVariable("id") Integer id, @RequestBody ClienteEntity cliente) {

        ClienteEntity updateCliente = consultorService.listarPorId(id);

        if (updateCliente != null) {
            cliente.setIdCliente(updateCliente.getIdCliente());
            return new ResponseEntity<>(consultorService.excluir(updateCliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ClienteEntity>> listar() {

        return new ResponseEntity<>(consultorService.listar(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<ClienteEntity>> listar(Pageable cliente) {

        Page<ClienteEntity> consultorEntityPage = consultorService.listar(cliente);
        return ResponseEntity.ok().body(consultorEntityPage);
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarClientesPage() {

        return new ResponseEntity<>(consultorService.listarClienteesPage(), HttpStatus.OK);
    }
}
