package br.com.supplier.portal.service.impl;

import br.com.supplier.portal.enums.StatusPonto;
import br.com.supplier.portal.model.entity.PontoEntity;
import br.com.supplier.portal.repository.PontoRepository;
import br.com.supplier.portal.service.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PontoServiceImpl implements PontoService {

    @Autowired
    PontoRepository pontoRepository;

    @Override
    public PontoEntity salvar(PontoEntity ponto) {
        PontoEntity pontoResponse = new PontoEntity(
                ponto.getDataPonto(),
                ponto.getDiaPonto(),
                ponto.getInicioPonto(),
                ponto.getFinalPonto(),
                ponto.getTotalHoraPonto(),
                ponto.getStatusPonto(),
                ponto.getTicketponto(),
                ponto.getAtividadePonto()
        );
        //pontoResponse.setAtividade(ponto.getAtividade());
        pontoResponse.setConsultor(ponto.getConsultor());
        pontoResponse.setCliente(ponto.getCliente());
        pontoRepository.save(ponto);
        return pontoResponse;
    }

    @Override
    public PontoEntity atualizar(PontoEntity ponto) {
        PontoEntity pontoResponse = PontoEntity.builder()
                .idPonto(ponto.getIdPonto())
                .dataPonto(ponto.getDataPonto())
                .diaPonto(ponto.getDiaPonto())
                .inicioPonto(ponto.getInicioPonto())
                .finalPonto(ponto.getFinalPonto())
                .totalHoraPonto(ponto.getTotalHoraPonto() == null ? totalHora(ponto) : ponto.getTotalHoraPonto())
                .statusPonto(ponto.getStatusPonto())
                .ticketponto(ponto.getTicketponto())
                .consultor(ponto.getConsultor())
                .cliente(ponto.getCliente())
                //.atividade(ponto.getAtividade())
                .build();

        pontoRepository.save(pontoResponse);
        return pontoResponse;
    }

    private Time totalHora(PontoEntity ponto){
        var hora = Duration.between(ponto.getInicioPonto().toLocalTime(), ponto.getFinalPonto().toLocalTime()).toHours();
        var minutos = Duration.between(ponto.getInicioPonto().toLocalTime(), ponto.getFinalPonto().toLocalTime()).toMinutesPart();
        return new Time((int) hora, minutos, 0);
    }


    @Override
    public PontoEntity excluir(PontoEntity ponto) {

        PontoEntity pontoResponse = PontoEntity.builder()
                .idPonto(ponto.getIdPonto())
                .statusPonto(StatusPonto.CANCELADO)
                .build();

        pontoRepository.save(pontoResponse);
        return pontoResponse;
    }

    @Override
    public List<PontoEntity> listar() {
        return pontoRepository.findAll();
    }

    @Override
    public Page<PontoEntity> listar(Pageable ponto) {
        Page<PontoEntity> pontoEntityPage = pontoRepository.findAll(ponto);
        return pontoEntityPage;
    }

/*    @Override
    public Map<String, Object> listarConsultoresPage() {
        return null;
    }*/

    /*public ResponseEntity<Map<String, Object>> findAll_(@RequestParam(required = false) String title,
                                       @RequestParam(defaultValue = "0") int page,

                                       @RequestParam(defaultValue = "3") int size) {*/
    @Override
    public Map<String, Object> listarPontosPage() {
        String title = "Pagination";
        int page = 0;
        int size = 3;

        List<PontoEntity> pontos = new ArrayList<PontoEntity>();
        Pageable pageable = PageRequest.of(page, size);

        Page<PontoEntity> pagePontos = pontoRepository.findAll(pageable);

        /*if (title == null)
            pageConsultores = consultorRepository.findAll(pageable);
        else
            pageConsultores = consultorRepository.findByTitleContaining(title, pageable);*/

        pontos = pagePontos.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("Pontos: ", pontos);
        response.put("Current Page: ", pagePontos.getNumber());
        response.put("Total Pontos: ", pagePontos.getTotalElements());
        response.put("Total Pages: ", pagePontos.getTotalPages());

        //return new ResponseEntity<>(response, HttpStatus.OK);
        return response;
    }

    @Override
    public PontoEntity listarPorId(Integer id) {
        return pontoRepository.findById(id).stream()
                .filter(ponto -> ponto.getIdPonto() != null)
                .findFirst()
                .get();
    }

}
