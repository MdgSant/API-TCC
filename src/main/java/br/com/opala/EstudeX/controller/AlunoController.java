package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Aluno;
import br.com.opala.EstudeX.entity.Utilizador;
import br.com.opala.EstudeX.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/alunos")
public class AlunoController
{
    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> listar()
    {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable("id") Integer id)
    {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/serie/{idSerie}")
    public List<Aluno> listarPorSerie(@PathVariable Integer idSerie)
    {
        return repository.buscarPorSerie(idSerie);
    }

    @PostMapping
    public Aluno cadastrar(@RequestBody Aluno aluno)
    {
        return repository.save(aluno);
    }//falar com o ronqui

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> alterar(@RequestBody Aluno aluno, @PathVariable Integer id)
    {
        if(id.equals(aluno.getId()) && repository.existsById(id))
        {
            return ResponseEntity.ok(repository.save(aluno));
        }
        return ResponseEntity.notFound().build();
    }


}
