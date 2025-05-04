package br.com.supplier.portal.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_consultor")
public class ConsultorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consultor", unique = true)
    private Integer idConsultor;

    @Column(name = "nome_consultor", unique = true)
    private String nomeConsultor;

    @Column(name = "cpf_consultor")
    private String cpfConsultor;

    @Column(name = "rg_consultor")
    private String rgConsultor;

    @Column(name = "endereco_consultor")
    private String enderecoConsultor;

    public ConsultorEntity(String nomeConsultor, String cpfConsultor, String rgConsultor, String enderecoConsultor) {
        this.nomeConsultor = nomeConsultor;
        this.cpfConsultor = cpfConsultor;
        this.rgConsultor = rgConsultor;
        this.enderecoConsultor = enderecoConsultor;
    }
}
