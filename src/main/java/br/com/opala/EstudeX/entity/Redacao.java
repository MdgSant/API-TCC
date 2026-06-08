package br.com.opala.EstudeX.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_REDACAO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Redacao
{
    @Id
    @Column(name = "idRedacao")
    private Integer idRedacao;

    @ManyToOne
    @JoinColumn(name = "idAluno")
    @JsonIgnoreProperties({"senha", "cpf", "foto", "tipoUtilizador", "serie", "uf", "cidade"})
    private Utilizador aluno;

    @Column(name = "Tema", nullable = false)
    private String tema;

    @Column(name = "Titulo", nullable = false)
    private String titulo;

    @Column(name = "TextoRedacao", nullable = false)
    private String textoRedacao;

    @Column(name = "PontuacaoObtida")
    private Float pontuacaoObtida;

    @Column(name = "Comentarios")
    private String comentarios;
}