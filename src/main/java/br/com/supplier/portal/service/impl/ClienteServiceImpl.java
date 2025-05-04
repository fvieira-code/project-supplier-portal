package br.com.supplier.portal.service.impl;

import br.com.supplier.portal.model.entity.ClienteEntity;
import br.com.supplier.portal.repository.ClienteRepository;
import br.com.supplier.portal.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository ClienteRepository;

    @Override
    public ClienteEntity salvar(ClienteEntity cliente) {
        ClienteEntity ClienteResponse = new ClienteEntity(
                cliente.getRazaoSocialCliente(),
                cliente.getNomeFantasiaCliente(),
                cliente.getCnpjCliente(),
                cliente.getEnderecoCliente()
        );

        ClienteRepository.save(cliente);
        return ClienteResponse;
    }

    @Override
    public ClienteEntity atualizar(ClienteEntity cliente) {
        ClienteEntity ClienteResponse = ClienteEntity.builder()
                .idCliente(cliente.getIdCliente())
                .razaoSocialCliente(cliente.getRazaoSocialCliente())
                .nomeFantasiaCliente(cliente.getNomeFantasiaCliente())
                .cnpjCliente(cliente.getCnpjCliente())
                .enderecoCliente(cliente.getEnderecoCliente())
                .build();

        ClienteRepository.save(ClienteResponse);
        return ClienteResponse;
    }

    /*public ClienteEntity atualizar(ClienteEntity cliente) {
        ClienteRepository.save(cliente);
        return cliente;
    }*/

    @Override
    public ClienteEntity excluir(ClienteEntity cliente) {

        ClienteEntity ClienteResponse = ClienteEntity.builder()
                .idCliente(cliente.getIdCliente())
                .razaoSocialCliente("CANCELADO")
                .nomeFantasiaCliente("CANCELADO")
                .cnpjCliente("CANCELADO")
                .enderecoCliente(cliente.getEnderecoCliente().concat("CANCELADO"))
                .build();

        ClienteRepository.save(ClienteResponse);
        return ClienteResponse;
    }

    @Override
    public List<ClienteEntity> listar() {
        return ClienteRepository.findAll();
    }

    @Override
    public Page<ClienteEntity> listar(Pageable cliente) {
        Page<ClienteEntity> ClienteEntityPage = ClienteRepository.findAll(cliente);
        return ClienteEntityPage;
    }

/*    @Override
    public Map<String, Object> listarClienteesPage() {
        return null;
    }*/

    /*public ResponseEntity<Map<String, Object>> findAll_(@RequestParam(required = false) String title,
                                       @RequestParam(defaultValue = "0") int page,

                                       @RequestParam(defaultValue = "3") int size) {*/
    @Override
    public Map<String, Object> listarClienteesPage() {
        String title = "Pagination";
        int page = 0;
        int size = 3;

        List<ClienteEntity> Clientees = new ArrayList<ClienteEntity>();
        Pageable pageable = PageRequest.of(page, size);

        Page<ClienteEntity> pageClientees;
        pageClientees = ClienteRepository.findAll(pageable);

        /*if (title == null)
            pageClientees = ClienteRepository.findAll(pageable);
        else
            pageClientees = ClienteRepository.findByTitleContaining(title, pageable);*/

        Clientees = pageClientees.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("Clientees: ", Clientees);
        response.put("Current Page: ", pageClientees.getNumber());
        response.put("Total Clientees: ", pageClientees.getTotalElements());
        response.put("Total Pages: ", pageClientees.getTotalPages());

        //return new ResponseEntity<>(response, HttpStatus.OK);
        return response;
    }

    @Override
    public ClienteEntity listarPorId(Integer id) {
        return ClienteRepository.findById(id).stream()
                .filter(cliente -> cliente.getIdCliente() != null)
                .findFirst()
                .get();
    }

}
