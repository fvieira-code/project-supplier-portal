package br.com.supplier.portal.model.entity;

import br.com.supplier.portal.enums.StatusPonto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Duration;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_ponto")
public class PontoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ponto")
    private Integer idPonto;

    @Column(name = "data_ponto")
    private LocalDate dataPonto;

    @Column(name = "dia_ponto", unique = true)
    private String diaPonto;

    @Column(name = "inicio_ponto")
    private Duration inicioPonto;

    @Column(name = "final_ponto")
    private Duration finalPonto;

    @Column(name = "total_hora_ponto")
    private Duration totalHoraPonto;

    @Column(name = "status_ponto")
    @Enumerated(EnumType.STRING)
    private StatusPonto statusPonto;

    @Column(name = "ticket_ponto")
    private String ticketponto;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_consultor", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ConsultorEntity consultor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_atividade", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AtividadeEntity atividade;

}
