package br.com.supplier.portal.model.entity;

import br.com.supplier.portal.enums.StatusAtividade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_atividade")
public class AtividadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atividade", unique = true)
    private Integer idAtividade;

    @Column(name = "descricao_atividade", unique = true)
    private String descricaoAtividade;

    @Column(name = "ticket_atividade")
    private String ticketAtividade;

    @Column(name = "status_atividade")
    @Enumerated(EnumType.STRING)
    private StatusAtividade statusAtividade;

    public AtividadeEntity(String descricaoAtividade, String ticketAtividade, StatusAtividade statusAtividade) {
        this.descricaoAtividade = descricaoAtividade;
        this.ticketAtividade = ticketAtividade;
        this.statusAtividade = statusAtividade;
    }
}
