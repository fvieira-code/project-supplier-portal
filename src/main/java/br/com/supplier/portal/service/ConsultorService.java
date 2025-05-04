package br.com.supplier.portal.service;

import br.com.supplier.portal.model.entity.ConsultorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ConsultorService {

    ConsultorEntity salvar(ConsultorEntity consultor);
    ConsultorEntity atualizar(ConsultorEntity consultor);
    ConsultorEntity excluir(ConsultorEntity consultor);
    List<ConsultorEntity> listar();
    Page<ConsultorEntity> listar(Pageable consultor);
    Map<String, Object> listarConsultoresPage();
    ConsultorEntity listarPorId(Integer id);

}
