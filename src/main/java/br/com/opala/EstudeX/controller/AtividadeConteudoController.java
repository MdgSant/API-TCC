package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Atividade;
import br.com.opala.EstudeX.entity.AtividadeConteudo;
import br.com.opala.EstudeX.repository.AtividadeConteudoRepository;
import br.com.opala.EstudeX.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/atividadeconteudo")
public class AtividadeConteudoController {

    @Autowired
    private AtividadeConteudoRepository repository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    // Busca o PDF da atividade
    @GetMapping("/{id}/arquivo")
    public ResponseEntity<byte[]> buscarArquivo(@PathVariable Integer id) {
        return repository.findById(id)
                .filter(c -> c.getArquivo() != null)
                .map(c -> ResponseEntity.ok()
                        .header("Content-Type", "application/pdf")
                        .body(c.getArquivo()))
                .orElse(ResponseEntity.notFound().build());
    }

    // Salva o PDF da atividade
    @PostMapping("/{id}/arquivo")
    public ResponseEntity<?> salvarArquivo(@PathVariable Integer id, @RequestBody byte[] arquivo) {
        Atividade atividade = atividadeRepository.findById(id).orElseThrow();

        AtividadeConteudo conteudo = repository.findById(id).orElse(new AtividadeConteudo());
        conteudo.setAtividade(atividade);
        conteudo.setArquivo(arquivo);
        repository.save(conteudo);

        return ResponseEntity.ok().build();
    }

    // Verifica se a atividade tem PDF
    @GetMapping("/{id}/temArquivo")
    public ResponseEntity<Boolean> temArquivo(@PathVariable Integer id) {
        boolean tem = repository.findById(id)
                .map(c -> c.getArquivo() != null)
                .orElse(false);
        return ResponseEntity.ok(tem);
    }
}