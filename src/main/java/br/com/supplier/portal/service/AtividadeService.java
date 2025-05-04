package br.com.supplier.portal.service;

import br.com.supplier.portal.model.entity.AtividadeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface AtividadeService {

    AtividadeEntity salvar(AtividadeEntity atividade);
    AtividadeEntity atualizar(AtividadeEntity atividade);
    AtividadeEntity excluir(AtividadeEntity atividade);
    List<AtividadeEntity> listar();
    Page<AtividadeEntity> listar(Pageable atividade);
    Map<String, Object> listarAtividadesPage();
    AtividadeEntity listarPorId(Integer id);

}
