package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.TelefoneRequestDTO;
import com.autobots.automanager.DTO.TelefoneResponseDTO;
import com.autobots.automanager.servicos.TelefoneService;

@RestController
@RequestMapping("/telefones")
public class TelefoneControle {

    @Autowired
    private TelefoneService servico;

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneResponseDTO> obterTelefone(@PathVariable Long id) {
        TelefoneResponseDTO telefone = servico.buscarPorId(id);
        if (telefone == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(telefone);
    }

    @GetMapping
    public ResponseEntity<List<TelefoneResponseDTO>> obterTelefones() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<TelefoneResponseDTO> cadastrarTelefone(@RequestBody TelefoneRequestDTO dto) {
        TelefoneResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/telefones/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneResponseDTO> atualizarTelefone(@PathVariable Long id, @RequestBody TelefoneRequestDTO dto) {
        TelefoneResponseDTO atualizado = servico.atualizar(id, dto);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTelefone(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
