package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.EmailRequestDTO;
import com.autobots.automanager.DTO.EmailResponseDTO;
import com.autobots.automanager.servicos.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailControle {

    @Autowired
    private EmailService servico;

    @GetMapping("/{id}")
    public ResponseEntity<EmailResponseDTO> obterEmail(@PathVariable Long id) {
        EmailResponseDTO email = servico.buscarPorId(id);
        if (email == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(email);
    }

    @GetMapping
    public ResponseEntity<List<EmailResponseDTO>> obterEmails() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<EmailResponseDTO> cadastrarEmail(@RequestBody EmailRequestDTO dto) {
        EmailResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/emails/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailResponseDTO> atualizarEmail(@PathVariable Long id, @RequestBody EmailRequestDTO dto) {
        EmailResponseDTO atualizado = servico.atualizar(id, dto);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEmail(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
