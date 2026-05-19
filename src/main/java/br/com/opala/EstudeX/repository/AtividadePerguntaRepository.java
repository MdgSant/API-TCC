package br.com.opala.EstudeX.repository;

import br.com.opala.EstudeX.entity.AtividadePergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadePerguntaRepository extends JpaRepository<AtividadePergunta, Integer>
{
    void deleteByAtividade_IdAtividade(Integer idAtividade);
}