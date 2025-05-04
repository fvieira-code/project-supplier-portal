package br.com.supplier.portal.model.dto;

import br.com.supplier.portal.enums.StatusAtividade;
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
public class AtividadeDto {

    private Integer idAtividade;
    private String descricaoAtividade;
    private String ticketAtividade;
    private StatusAtividade statusAtividade;

}
