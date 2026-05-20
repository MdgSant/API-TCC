package br.com.opala.EstudeX.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TBL_RESPOSTADUVIDA")
public class RespostaDuvida {

    @Id
    @Column(name = "idDuvida")
    private Integer idDuvida;

    @Column(name = "Momento")
    private LocalDateTime momento;

    @Column(name = "ConteudoResposta")
    private String conteudoResposta;

    @ManyToOne
    @JoinColumn(name = "idDuvida", insertable = false, updatable = false)
    private Duvida duvida;

    @ManyToOne
    @JoinColumn(name = "idUtilizador")
    private Utilizador utilizador;
}
