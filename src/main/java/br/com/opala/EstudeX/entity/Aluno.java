package br.com.opala.EstudeX.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_ALUNO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aluno extends Utilizador
{

    @Column(name = "xp")
    private Integer xp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSerie")
    @JsonIgnoreProperties({"alunos", "comunicados"})
    private Serie serie;

}