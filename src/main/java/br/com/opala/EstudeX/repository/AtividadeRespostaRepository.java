package br.com.opala.EstudeX.repository;

import br.com.opala.EstudeX.entity.AtividadeResposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRespostaRepository extends JpaRepository<AtividadeResposta, Integer>
{
    List<AtividadeResposta> findByAtividade_IdAtividade(Integer idAtividade);
    void deleteByAtividade_IdAtividade(Integer idAtividade);
}
