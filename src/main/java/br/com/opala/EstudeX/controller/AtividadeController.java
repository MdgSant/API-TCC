package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Atividade;
import br.com.opala.EstudeX.repository.AtividadePerguntaRepository;
import br.com.opala.EstudeX.repository.AtividadeRepository;
import br.com.opala.EstudeX.repository.AtividadeRespostaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/atividades")
public class AtividadeController
{
    @Autowired
    private AtividadeRepository repository;

    @Autowired
    private AtividadeRespostaRepository atividadeRespostaRepository;

    @Autowired
    private AtividadePerguntaRepository atividadePerguntaRepository;

    @GetMapping
    public List<Atividade> listar() {return repository.findAll();}

    @GetMapping("/{id}")
    public Atividade buscarPorId(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Atividade cadastrar(@RequestBody Atividade atividade)
    {
        return repository.save(atividade);
    }

    @PatchMapping("/{id}/pontuacao")
    public Atividade atualizarPontuacao(@PathVariable Integer id, @RequestParam Integer pontuacaoMaxima) {
        Atividade atividade = repository.findById(id).orElseThrow();
        atividade.setPontuacaoMaxima(pontuacaoMaxima);
        return repository.save(atividade);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        atividadeRespostaRepository.deleteByAtividade_IdAtividade(id);
        atividadePerguntaRepository.deleteByAtividade_IdAtividade(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
