package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Comunicado;
import br.com.opala.EstudeX.repository.ComunicadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comunicados")
public class ComunicadoController
{
    @Autowired
    private ComunicadoRepository repository;

    @GetMapping
    public List<Comunicado> listar() {return repository.findAll();}

    @PostMapping
    public Comunicado cadastrar(@RequestBody Comunicado comunicado)
    {
        return repository.save(comunicado);
    }

    @GetMapping("/serie/{idSerie}")
    public List<Comunicado> listarPorSerie(@PathVariable Integer idSerie) {
        return repository.findBySerie_Id(idSerie);
    }

    @GetMapping("/serie/{idSerie}/disciplina/{idDisciplina}")
    public List<Comunicado> listarPorSerieEDisciplina(
            @PathVariable Integer idSerie,
            @PathVariable Integer idDisciplina) {
        return repository.findBySerie_IdAndDisciplina_Id(idSerie, idDisciplina);
    }
}
