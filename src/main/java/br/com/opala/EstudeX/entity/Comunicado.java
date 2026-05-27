package br.com.opala.EstudeX.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "TBL_COMUNICADO")
public class Comunicado
{
    @Id
    @Column(name = "idComunicado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdComunicado;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "DataEnvio")
    private LocalDate dataEnvio;

    @Column(name = "UtilizadorResponsavel")
    private String UtilizadorResponsavel;

    @Column(name = "DataPublicacao")
    private Timestamp dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "idSerie")
    private Serie serie;

    @ManyToOne
    @JoinColumn(name = "idDisciplina")
    private Disciplina disciplina;

    @PrePersist
    public void prePersist() {
        this.dataPublicacao = new Timestamp(System.currentTimeMillis());
    }


}
