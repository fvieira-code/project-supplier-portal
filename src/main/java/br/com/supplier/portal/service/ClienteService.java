package br.com.supplier.portal.service;

import br.com.supplier.portal.model.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ClienteService {

    ClienteEntity salvar(ClienteEntity cliente);
    ClienteEntity atualizar(ClienteEntity cliente);
    ClienteEntity excluir(ClienteEntity cliente);
    List<ClienteEntity> listar();
    Page<ClienteEntity> listar(Pageable cliente);
    Map<String, Object> listarClienteesPage();
    ClienteEntity listarPorId(Integer id);

}
