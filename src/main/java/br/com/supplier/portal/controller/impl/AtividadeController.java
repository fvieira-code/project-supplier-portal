package br.com.supplier.portal.controller.impl;

import br.com.supplier.portal.controller.AtividadeControllerApi;
import br.com.supplier.portal.model.entity.AtividadeEntity;
import br.com.supplier.portal.service.AtividadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Atividade", description = "Atividade Controller.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AtividadeController implements AtividadeControllerApi {

    @Autowired
    private AtividadeService atividadeService;

    @Override
    public ResponseEntity<AtividadeEntity> salvar(@RequestBody AtividadeEntity atividade) {

        AtividadeEntity atividadeResponse = atividadeService.salvar(atividade);
        return new ResponseEntity<>(atividadeResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AtividadeEntity> atualizar(@PathVariable("id") Integer id, @RequestBody AtividadeEntity atividade) {

        AtividadeEntity updateAtividade = atividadeService.listarPorId(id);
        if (updateAtividade != null) {
            atividade.setIdAtividade(updateAtividade.getIdAtividade());
            return new ResponseEntity<>(atividadeService.atualizar(atividade), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<AtividadeEntity> excluir(@PathVariable("id") Integer id, @RequestBody AtividadeEntity atividade) {

        AtividadeEntity updateAtividade = atividadeService.listarPorId(id);

        if (updateAtividade != null) {
            atividade.setIdAtividade(updateAtividade.getIdAtividade());
            return new ResponseEntity<>(atividadeService.excluir(updateAtividade), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<AtividadeEntity>> listar() {

        return new ResponseEntity<>(atividadeService.listar(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<AtividadeEntity>> listar(Pageable atividade) {

        Page<AtividadeEntity> atividadeEntityPage = atividadeService.listar(atividade);
        return ResponseEntity.ok().body(atividadeEntityPage);
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarAtividadesPage() {

        return new ResponseEntity<>(atividadeService.listarAtividadesPage(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AtividadeEntity>> listarPorDescricao(@RequestParam(required = false) String descricao) {

        var atividades = atividadeService.listarPorDescricao(descricao);
        try{
            if (atividades.isEmpty() || atividades == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(atividades, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<AtividadeEntity> listarPorId(@PathVariable("id") Integer id) {
        //Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        var atividade = atividadeService.listarPorId(id);

        if (atividade != null) {
            return new ResponseEntity<>(atividade, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
