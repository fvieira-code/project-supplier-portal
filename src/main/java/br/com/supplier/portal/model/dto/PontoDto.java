package br.com.supplier.portal.model.dto;

import br.com.supplier.portal.enums.StatusPonto;
import br.com.supplier.portal.model.entity.AtividadeEntity;
import br.com.supplier.portal.model.entity.ClienteEntity;
import br.com.supplier.portal.model.entity.ConsultorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PontoDto {

    private Integer idPonto;
    private LocalDate dataPonto;
    private String diaPonto;
    private Duration inicioPonto;
    private Duration finalPonto;
    private Duration totalHoraPonto;
    private StatusPonto statusPonto;
    private String ticketponto;
    /* Many-To-One*/
    private ConsultorEntity consultor;
    private ClienteEntity cliente;
    private AtividadeEntity atividade;

}
