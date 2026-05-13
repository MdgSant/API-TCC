package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Aluno;
import br.com.opala.EstudeX.entity.Disciplina;
import br.com.opala.EstudeX.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/disciplinas")
public class DisciplinaController
{
    @Autowired
    private DisciplinaRepository repository;

    @GetMapping
    public List<Disciplina> listar()
    {
        return repository.findAll();
    }
}
