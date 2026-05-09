package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.CredencialUsuarioSenhaRequestDTO;
import com.autobots.automanager.DTO.CredencialUsuarioSenhaResponseDTO;
import com.autobots.automanager.servicos.CredencialUsuarioSenhaService;

@RestController
@RequestMapping("/credenciais-senha")
public class CredencialUsuarioSenhaControle {

    @Autowired
    private CredencialUsuarioSenhaService servico;

    @GetMapping("/{id}")
    public ResponseEntity<CredencialUsuarioSenhaResponseDTO> obterCredencial(@PathVariable Long id) {
        CredencialUsuarioSenhaResponseDTO resposta = servico.buscarPorId(id);
        if (resposta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<CredencialUsuarioSenhaResponseDTO>> obterCredenciais() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<CredencialUsuarioSenhaResponseDTO> cadastrarCredencial(@RequestBody CredencialUsuarioSenhaRequestDTO dto) {
        CredencialUsuarioSenhaResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/credenciais-senha/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredencialUsuarioSenhaResponseDTO> atualizarCredencial(@PathVariable Long id, @RequestBody CredencialUsuarioSenhaRequestDTO dto) {
        CredencialUsuarioSenhaResponseDTO atualizado = servico.atualizar(id, dto);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCredencial(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}