package br.com.supplier.portal.controller.impl;

import br.com.supplier.portal.controller.PontoControllerApi;
import br.com.supplier.portal.model.entity.PontoEntity;
import br.com.supplier.portal.service.PontoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Ponto", description = "ponto Controller.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class PontoController implements PontoControllerApi {

    @Autowired
    private PontoService pontoService;

    @Override
    public ResponseEntity<PontoEntity> salvar(@RequestBody PontoEntity ponto) {

        PontoEntity pontoResponse = pontoService.salvar(ponto);
        return new ResponseEntity<>(pontoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PontoEntity> atualizar(@PathVariable("id") Integer id, @RequestBody PontoEntity ponto) {

        PontoEntity updatePonto = pontoService.listarPorId(id);
        if (updatePonto != null) {
            ponto.setIdPonto(updatePonto.getIdPonto());
            return new ResponseEntity<>(pontoService.atualizar(ponto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<PontoEntity> excluir(@PathVariable("id") Integer id, @RequestBody PontoEntity ponto) {

        PontoEntity updatePonto = pontoService.listarPorId(id);

        if (updatePonto != null) {
            ponto.setIdPonto(updatePonto.getIdPonto());
            return new ResponseEntity<>(pontoService.excluir(updatePonto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<PontoEntity>> listar() {

        return new ResponseEntity<>(pontoService.listar(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<PontoEntity>> listar(Pageable ponto) {

        Page<PontoEntity> pontoEntityPage = pontoService.listar(ponto);
        return ResponseEntity.ok().body(pontoEntityPage);
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarConsultoresPage() {

        return new ResponseEntity<>(pontoService.listarPontosPage(), HttpStatus.OK);
    }
}
