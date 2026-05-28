package br.com.opala.EstudeX.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_ATIVIDADECONTEUDO")
public class AtividadeConteudo
{
    @Id
    @Column(name = "idAtividade")
    private Integer idAtividade;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idAtividade")
    private Atividade atividade;

    @Column(name = "Arquivo")
    private byte[] arquivo;
}
