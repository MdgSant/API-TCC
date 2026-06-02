package br.com.opala.EstudeX.repository;

import br.com.opala.EstudeX.entity.Redacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedacaoRepository extends JpaRepository<Redacao, Integer>
{
    @Query("SELECT r FROM Redacao r WHERE r.aluno.id = :idAluno")
    List<Redacao> findByAlunoId(@Param("idAluno") Integer idAluno);

    @Query("SELECT r FROM Redacao r WHERE r.pontuacaoObtida IS NULL")
    List<Redacao> findPendentes();

    @Query("SELECT COALESCE(MAX(r.idRedacao), 0) FROM Redacao r")
    Integer getMaxId();
}