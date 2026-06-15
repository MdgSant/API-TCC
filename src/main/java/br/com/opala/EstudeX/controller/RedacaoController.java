package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Redacao;
import br.com.opala.EstudeX.entity.Utilizador;
import br.com.opala.EstudeX.repository.RedacaoRepository;
import br.com.opala.EstudeX.repository.CorrecaoRedacaoRepository;
import br.com.opala.EstudeX.repository.UtilizadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/redacoes")
public class RedacaoController {
    @Autowired
    private RedacaoRepository repository;

    @Autowired
    private CorrecaoRedacaoRepository correcaoRedacaoRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @GetMapping
    public List<Redacao> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Redacao redacao = repository.findById(id).orElseThrow();

        String nomeSerie = "Não definida";
        try {
            nomeSerie = repository.findSerieByAlunoId(redacao.getAluno().getId());
            if (nomeSerie == null)
                nomeSerie = "Não definida";
        } catch (Exception ignored) {
        }

        Map<String, Object> resultado = new LinkedHashMap<>();
        resultado.put("idRedacao", redacao.getIdRedacao());
        resultado.put("aluno", redacao.getAluno());
        resultado.put("tema", redacao.getTema());
        resultado.put("titulo", redacao.getTitulo());
        resultado.put("textoRedacao", redacao.getTextoRedacao());
        resultado.put("pontuacaoObtida", redacao.getPontuacaoObtida());
        resultado.put("comentarios", redacao.getComentarios());
        resultado.put("turma", nomeSerie);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/aluno/{idAluno}")
    public List<Redacao> listarPorAluno(@PathVariable Integer idAluno) {
        return repository.findByAlunoId(idAluno);
    }

    @GetMapping("/pendentes")
    public List<Redacao> listarPendentes() {
        return repository.findPendentes();
    }

    @PostMapping
    public Redacao cadastrar(@RequestBody Redacao redacao) {
        return repository.save(redacao);
    }

    @PatchMapping("/{id}/pontuacao")
    public Redacao atualizarPontuacao(@PathVariable Integer id, @RequestParam Float pontuacaoObtida) {
        Redacao redacao = repository.findById(id).orElseThrow();
        redacao.setPontuacaoObtida(pontuacaoObtida);
        return repository.save(redacao);
    }

    @PatchMapping("/{id}/comentarios")
    public Redacao atualizarComentarios(@PathVariable Integer id, @RequestParam String comentarios) {
        Redacao redacao = repository.findById(id).orElseThrow();
        redacao.setComentarios(comentarios);
        return repository.save(redacao);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        correcaoRedacaoRepository.deleteByRedacao_IdRedacao(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ========== ENDPOINTS PARA PROFESSOR ==========

    @GetMapping("/professor/{idProfessor}")
    public List<Redacao> listarPropostasProfessor(@PathVariable Integer idProfessor) {
        return repository.findByAlunoId(idProfessor);
    }

    @PostMapping("/professor")
    public ResponseEntity<?> criarProposta(@RequestBody Map<String, Object> payload) {
        try {
            Redacao redacao = new Redacao();

            // Usar o professor como "aluno placeholder" (ID 6)
            Utilizador professor = utilizadorRepository.findById(6).orElse(null);

            if (professor == null) {
                return ResponseEntity.status(404).body("Professor não encontrado");
            }

            redacao.setAluno(professor);
            redacao.setTitulo((String) payload.get("titulo"));
            redacao.setTema((String) payload.get("tema"));
            redacao.setTextoRedacao((String) payload.get("textoRedacao"));
            redacao.setPontuacaoObtida(null);
            redacao.setComentarios(null);

            Redacao saved = repository.save(redacao);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }
}