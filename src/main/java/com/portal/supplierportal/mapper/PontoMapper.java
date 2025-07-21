package com.portal.supplierportal.mapper;

import com.portal.supplierportal.dto.PontoDTO;
import com.portal.supplierportal.model.*;

public class PontoMapper {

    public static PontoDTO toDTO(Ponto entity) {
        return PontoDTO.builder()
                .id(entity.getId())
                .atividade(entity.getAtividade())
                .data(entity.getData())
                .dia(entity.getDia())
                .inicio(entity.getInicio())
                .fim(entity.getFim())
                .total(entity.getTotal())
                .status(entity.getStatus())
                .ticket(entity.getTicket())
                .idConsultor(entity.getConsultor().getId())
                .idCliente(entity.getCliente().getId())
                .build();
    }

    public static Ponto toEntity(PontoDTO dto, Consultor consultor, Cliente cliente) {
        return Ponto.builder()
                .id(dto.getId())
                .atividade(dto.getAtividade())
                .data(dto.getData())
                .dia(dto.getDia())
                .inicio(dto.getInicio())
                .fim(dto.getFim())
                .total(dto.getTotal())
                .status(dto.getStatus())
                .ticket(dto.getTicket())
                .consultor(consultor)
                .cliente(cliente)
                .build();
    }
}
