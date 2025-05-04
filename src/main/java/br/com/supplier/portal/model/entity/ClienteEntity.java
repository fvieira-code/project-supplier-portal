package br.com.supplier.portal.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", unique = true)
    private Integer idCliente;

    @Column(name = "razao_social_cliente", unique = true)
    private String razaoSocialCliente;

    @Column(name = "nome_fantasia_cliente")
    private String nomeFantasiaCliente;

    @Column(name = "cnpj_cliente")
    private String cnpjCliente;

    @Column(name = "endereco_cliente")
    private String enderecoCliente;

    public ClienteEntity(String razaoSocialCliente, String nomeFantasiaCliente, String cnpjCliente, String enderecoCliente) {
        this.razaoSocialCliente = razaoSocialCliente;
        this.nomeFantasiaCliente = nomeFantasiaCliente;
        this.cnpjCliente = cnpjCliente;
        this.enderecoCliente = enderecoCliente;
    }
}
