package br.com.opala.EstudeX.repository;

import br.com.opala.EstudeX.entity.Comunicado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComunicadoRepository extends JpaRepository<Comunicado, Integer> {
    List<Comunicado> findBySerie_Id(Integer idSerie);
    List<Comunicado> findBySerie_IdAndDisciplina_Id(Integer idSerie, Integer idDisciplina);
}
