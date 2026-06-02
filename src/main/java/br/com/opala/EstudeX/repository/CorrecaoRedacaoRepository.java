package br.com.opala.EstudeX.repository;

import br.com.opala.EstudeX.entity.CorrecaoRedacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorrecaoRedacaoRepository extends JpaRepository<CorrecaoRedacao, Integer>
{
    List<CorrecaoRedacao> findByRedacao_IdRedacao(Integer idRedacao);

    List<CorrecaoRedacao> findByUtilizador_Id(Integer idUtilizador);

    void deleteByRedacao_IdRedacao(Integer idRedacao);
}