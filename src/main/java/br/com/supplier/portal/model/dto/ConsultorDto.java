package br.com.supplier.portal.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultorDto {

    private Integer idConsultor;
    private String nomeConsultor;
    private String cpfConsultor;
    private String rgConsultor;
    private String enderecoConsultor;

}
