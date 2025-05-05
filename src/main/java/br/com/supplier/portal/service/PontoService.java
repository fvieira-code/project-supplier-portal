package br.com.supplier.portal.service;

import br.com.supplier.portal.model.entity.PontoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PontoService {

    PontoEntity salvar(PontoEntity ponto);
    PontoEntity atualizar(PontoEntity ponto);
    PontoEntity excluir(PontoEntity ponto);
    List<PontoEntity> listar();
    Page<PontoEntity> listar(Pageable ponto);
    Map<String, Object> listarPontosPage();
    PontoEntity listarPorId(Integer id);

}
