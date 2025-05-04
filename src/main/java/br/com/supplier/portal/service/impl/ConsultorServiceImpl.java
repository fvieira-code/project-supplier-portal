package br.com.supplier.portal.service.impl;

import br.com.supplier.portal.model.entity.ConsultorEntity;
import br.com.supplier.portal.repository.ConsultorRepository;
import br.com.supplier.portal.service.ConsultorService;
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
public class ConsultorServiceImpl implements ConsultorService {

    @Autowired
    ConsultorRepository consultorRepository;

    @Override
    public ConsultorEntity salvar(ConsultorEntity consultor) {
        ConsultorEntity consultorResponse = new ConsultorEntity(
                consultor.getNomeConsultor(),
                consultor.getCpfConsultor(),
                consultor.getRgConsultor(),
                consultor.getEnderecoConsultor()
        );

        consultorRepository.save(consultor);
        return consultorResponse;
    }

    @Override
    public ConsultorEntity atualizar(ConsultorEntity consultor) {
        ConsultorEntity consultorResponse = ConsultorEntity.builder()
                .idConsultor(consultor.getIdConsultor())
                .nomeConsultor(consultor.getNomeConsultor())
                .cpfConsultor(consultor.getCpfConsultor())
                .rgConsultor(consultor.getRgConsultor())
                .enderecoConsultor(consultor.getEnderecoConsultor())
                .build();

        consultorRepository.save(consultorResponse);
        return consultorResponse;
    }

    /*public ConsultorEntity atualizar(ConsultorEntity consultor) {
        consultorRepository.save(consultor);
        return consultor;
    }*/

    @Override
    public ConsultorEntity excluir(ConsultorEntity consultor) {

        ConsultorEntity consultorResponse = ConsultorEntity.builder()
                .idConsultor(consultor.getIdConsultor())
                .nomeConsultor(consultor.getNomeConsultor().concat("CANCELADO"))
                .cpfConsultor(/*consultor.getCpfConsultor().concat(*/"CANCELADO")
                .rgConsultor(/*consultor.getRgConsultor().concat(*/" - CANCELADO")
                .enderecoConsultor(consultor.getEnderecoConsultor().concat("CANCELADO"))
                .build();

        consultorRepository.save(consultorResponse);
        return consultorResponse;
    }

    @Override
    public List<ConsultorEntity> listar() {
        return consultorRepository.findAll();
    }

    @Override
    public Page<ConsultorEntity> listar(Pageable consultor) {
        Page<ConsultorEntity> consultorEntityPage = consultorRepository.findAll(consultor);
        return consultorEntityPage;
    }

/*    @Override
    public Map<String, Object> listarConsultoresPage() {
        return null;
    }*/

    /*public ResponseEntity<Map<String, Object>> findAll_(@RequestParam(required = false) String title,
                                       @RequestParam(defaultValue = "0") int page,

                                       @RequestParam(defaultValue = "3") int size) {*/
    @Override
    public Map<String, Object> listarConsultoresPage() {
        String title = "Pagination";
        int page = 0;
        int size = 3;

        List<ConsultorEntity> consultores = new ArrayList<ConsultorEntity>();
        Pageable pageable = PageRequest.of(page, size);

        Page<ConsultorEntity> pageConsultores;
        pageConsultores = consultorRepository.findAll(pageable);

        /*if (title == null)
            pageConsultores = consultorRepository.findAll(pageable);
        else
            pageConsultores = consultorRepository.findByTitleContaining(title, pageable);*/

        consultores = pageConsultores.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("Consultores: ", consultores);
        response.put("Current Page: ", pageConsultores.getNumber());
        response.put("Total Consultores: ", pageConsultores.getTotalElements());
        response.put("Total Pages: ", pageConsultores.getTotalPages());

        //return new ResponseEntity<>(response, HttpStatus.OK);
        return response;
    }

    @Override
    public ConsultorEntity listarPorId(Integer id) {
        return consultorRepository.findById(id).stream()
                .filter(consultor -> consultor.getIdConsultor() != null)
                .findFirst()
                .get();
    }

}
