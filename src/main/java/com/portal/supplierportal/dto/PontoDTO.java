package com.portal.supplierportal.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PontoDTO {
    private Integer id;

    @NotBlank
    @Size(max = 1000)
    private String atividade;

    @NotNull
    private LocalDate data;

    @NotBlank
    private String dia;

    private LocalTime inicio;
    private LocalTime fim;
    private LocalTime total;

    @NotBlank
    private String status;

    @NotBlank
    private String ticket;

    @NotNull
    private Integer idConsultor;

    @NotNull
    private Integer idCliente;
}
