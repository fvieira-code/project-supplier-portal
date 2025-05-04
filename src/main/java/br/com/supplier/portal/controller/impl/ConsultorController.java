package br.com.supplier.portal.controller.impl;

import br.com.supplier.portal.controller.ConsultorControllerApi;
import br.com.supplier.portal.model.entity.ConsultorEntity;
import br.com.supplier.portal.repository.ConsultorRepository;
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

@Tag(name = "Consultor", description = "Consultor Controller.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ConsultorController implements ConsultorControllerApi {

    @Autowired
    private ConsultorService consultorService;

    @Autowired
    private ConsultorRepository consultorRepository;

    @Override
    public ResponseEntity<ConsultorEntity> salvar(@RequestBody ConsultorEntity consultor) {

        ConsultorEntity consultorResponse = consultorService.salvar(consultor);
        return new ResponseEntity<>(consultorResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ConsultorEntity> atualizar(@PathVariable("id") Integer id, @RequestBody ConsultorEntity consultor) {

        ConsultorEntity updateConsultor = consultorService.listarPorId(id);
        if (updateConsultor != null) {
            consultor.setIdConsultor(updateConsultor.getIdConsultor());
            return new ResponseEntity<>(consultorService.atualizar(consultor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ConsultorEntity> excluir(@PathVariable("id") Integer id, @RequestBody ConsultorEntity consultor) {

        ConsultorEntity updateConsultor = consultorService.listarPorId(id);

        if (updateConsultor != null) {
            consultor.setIdConsultor(updateConsultor.getIdConsultor());
            return new ResponseEntity<>(consultorService.excluir(updateConsultor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ConsultorEntity>> listar() {

        return new ResponseEntity<>(consultorService.listar(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<ConsultorEntity>> listar(Pageable consultor) {

        Page<ConsultorEntity> consultorEntityPage = consultorService.listar(consultor);
        return ResponseEntity.ok().body(consultorEntityPage);
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarConsultoresPage() {

        return new ResponseEntity<>(consultorService.listarConsultoresPage(), HttpStatus.OK);
    }
}
