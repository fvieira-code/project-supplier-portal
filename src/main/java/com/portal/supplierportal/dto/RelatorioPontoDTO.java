package com.portal.supplierportal.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatorioPontoDTO {
    private List<PontoDTO> registros;
    private String totalHoras;
}
