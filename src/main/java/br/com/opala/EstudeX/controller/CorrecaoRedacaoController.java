package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.CorrecaoRedacao;
import br.com.opala.EstudeX.repository.CorrecaoRedacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/correcoes-redacao")
public class CorrecaoRedacaoController
{
    @Autowired
    private CorrecaoRedacaoRepository repository;

    @GetMapping
    public List<CorrecaoRedacao> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public CorrecaoRedacao buscarPorId(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @GetMapping("/redacao/{idRedacao}")
    public List<CorrecaoRedacao> listarPorRedacao(@PathVariable Integer idRedacao) {
        return repository.findByRedacao_IdRedacao(idRedacao);
    }

    @GetMapping("/professor/{idProfessor}")
    public List<CorrecaoRedacao> listarPorProfessor(@PathVariable Integer idProfessor) {
        return repository.findByUtilizador_Id(idProfessor);
    }

    @PostMapping
    public CorrecaoRedacao cadastrar(@RequestBody CorrecaoRedacao correcaoRedacao) {
        correcaoRedacao.setDataResposta(LocalDateTime.now());
        return repository.save(correcaoRedacao);
    }
}