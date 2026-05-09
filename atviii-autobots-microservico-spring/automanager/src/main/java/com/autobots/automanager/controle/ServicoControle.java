package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.ServicoRequestDTO;
import com.autobots.automanager.DTO.ServicoResponseDTO;
import com.autobots.automanager.servicos.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoControle {

    @Autowired
    private ServicoService servico;

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> obterServico(@PathVariable Long id) {
        ServicoResponseDTO retorno = servico.buscarPorId(id);
        if (retorno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(retorno);
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> obterServicos() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> cadastrarServico(@RequestBody ServicoRequestDTO dto) {
        ServicoResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/servicos/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizarServico(@PathVariable Long id, @RequestBody ServicoRequestDTO dto) {
        ServicoResponseDTO atualizado = servico.atualizar(id, dto);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirServico(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}