package br.com.supplier.portal.model.entity;

import br.com.supplier.portal.enums.StatusPonto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Time;
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
    private Time inicioPonto;

    @Column(name = "final_ponto")
    private Time finalPonto;

    @Column(name = "total_hora_ponto")
    private Time totalHoraPonto;

    @Column(name = "status_ponto")
    @Enumerated(EnumType.STRING)
    private StatusPonto statusPonto;

    @Column(name = "ticket_ponto")
    private String ticketponto;

    @Column(name = "atividade_ponto")
    private String atividadePonto;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_consultor", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ConsultorEntity consultor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClienteEntity cliente;

    /*@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_atividade", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AtividadeEntity atividade;*/

    public PontoEntity(LocalDate dataPonto, String diaPonto,
                       Time inicioPonto, Time finalPonto,
                       Time totalHoraPonto, StatusPonto statusPonto,
                       String ticketponto, String atividadePonto) {
        this.dataPonto = dataPonto;
        this.diaPonto = diaPonto;
        this.inicioPonto = inicioPonto;
        this.finalPonto = finalPonto;
        this.totalHoraPonto = totalHoraPonto;
        this.statusPonto = statusPonto;
        this.ticketponto = ticketponto;
        this.atividadePonto = atividadePonto;
    }

}
