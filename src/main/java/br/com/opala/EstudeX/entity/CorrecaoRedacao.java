package br.com.opala.EstudeX.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TBL_CORRECAOREDACAO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CorrecaoRedacao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCorrecaoRedacao")
    private Integer idCorrecaoRedacao;

    @ManyToOne
    @JoinColumn(name = "idRedacao")
    private Redacao redacao;

    @ManyToOne
    @JoinColumn(name = "idUtilizador")
    @JsonIgnoreProperties({"senha", "cpf", "foto", "tipoUtilizador", "serie", "uf", "cidade"})
    private Utilizador utilizador;

    @Column(name = "PontuacaoObtida")
    private Float pontuacaoObtida;

    @Column(name = "DataResposta")
    private LocalDateTime dataResposta;
}