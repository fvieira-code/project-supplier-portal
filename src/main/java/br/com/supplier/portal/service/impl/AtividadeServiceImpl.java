package br.com.supplier.portal.service.impl;

import br.com.supplier.portal.enums.StatusAtividade;
import br.com.supplier.portal.model.entity.AtividadeEntity;
import br.com.supplier.portal.repository.AtividadeRepository;
import br.com.supplier.portal.service.AtividadeService;
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
public class AtividadeServiceImpl implements AtividadeService {

    @Autowired
    AtividadeRepository atividadeRepository;

    @Override
    public AtividadeEntity salvar(AtividadeEntity atividade) {
        AtividadeEntity AtividadeResponse = new AtividadeEntity(
                atividade.getDescricaoAtividade(),
                atividade.getTicketAtividade(),
                atividade.getStatusAtividade()
        );

        atividadeRepository.save(atividade);
        return AtividadeResponse;
    }

    @Override
    public AtividadeEntity atualizar(AtividadeEntity atividade) {
        AtividadeEntity AtividadeResponse = AtividadeEntity.builder()
                .idAtividade(atividade.getIdAtividade())
                .descricaoAtividade(atividade.getDescricaoAtividade())
                .ticketAtividade(atividade.getTicketAtividade())
                .statusAtividade(atividade.getStatusAtividade())
                .build();

        atividadeRepository.save(AtividadeResponse);
        return AtividadeResponse;
    }

    @Override
    public AtividadeEntity excluir(AtividadeEntity atividade) {

        AtividadeEntity AtividadeResponse = AtividadeEntity.builder()
                .idAtividade(atividade.getIdAtividade())
                .descricaoAtividade(atividade.getDescricaoAtividade().concat(" - CANCELADO"))
                .ticketAtividade(atividade.getTicketAtividade().concat(""))
                .statusAtividade(StatusAtividade.INATIVO)
                .build();

        atividadeRepository.save(AtividadeResponse);
        return AtividadeResponse;
    }

    @Override
    public List<AtividadeEntity> listar() {
        return atividadeRepository.findAll();
    }

    @Override
    public Page<AtividadeEntity> listar(Pageable atividade) {
        Page<AtividadeEntity> atividadeEntityPage = atividadeRepository.findAll(atividade);
        return atividadeEntityPage;
    }

/*    @Override
    public Map<String, Object> listarAtividadeesPage() {
        return null;
    }*/

    /*public ResponseEntity<Map<String, Object>> findAll_(@RequestParam(required = false) String title,
                                       @RequestParam(defaultValue = "0") int page,

                                       @RequestParam(defaultValue = "3") int size) {*/
    @Override
    public Map<String, Object> listarAtividadesPage() {
        String title = "Pagination";
        int page = 0;
        int size = 3;

        List<AtividadeEntity> Atividadees = new ArrayList<AtividadeEntity>();
        Pageable pageable = PageRequest.of(page, size);

        Page<AtividadeEntity> pageAtividades;
        pageAtividades = atividadeRepository.findAll(pageable);

        /*if (title == null)
            pageAtividadees = AtividadeRepository.findAll(pageable);
        else
            pageAtividadees = AtividadeRepository.findByTitleContaining(title, pageable);*/

        Atividadees = pageAtividades.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("Atividadees: ", Atividadees);
        response.put("Current Page: ", pageAtividades.getNumber());
        response.put("Total Atividadees: ", pageAtividades.getTotalElements());
        response.put("Total Pages: ", pageAtividades.getTotalPages());

        return response;
    }

    @Override
    public AtividadeEntity listarPorId(Integer id) {
        return atividadeRepository.findById(id).stream()
                .filter(atividade -> atividade.getIdAtividade() != null)
                .findFirst()
                .get();
    }

    @Override
    public List<AtividadeEntity> listarPorDescricao(String descricao) {
        try {
            List<AtividadeEntity> atividades = new ArrayList<AtividadeEntity>();

            if (descricao == null)
                atividadeRepository.findAll().forEach(atividades::add);
            else
                atividadeRepository.findByDescricaoAtividade(descricao).forEach(atividades::add);

            return atividades;
        } catch (Exception e) {
            return null;
        }
    }

}
