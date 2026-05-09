package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.CredencialCodigoBarraRequestDTO;
import com.autobots.automanager.DTO.CredencialCodigoBarraResponseDTO;
import com.autobots.automanager.servicos.CredencialCodigoBarraService;

@RestController
@RequestMapping("/credenciais-codigo-barra")
public class CredencialCodigoBarraControle {

    @Autowired
    private CredencialCodigoBarraService servico;

    @GetMapping("/{id}")
    public ResponseEntity<CredencialCodigoBarraResponseDTO> obterCredencial(@PathVariable Long id) {
        CredencialCodigoBarraResponseDTO credencial = servico.buscarPorId(id);
        if (credencial == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(credencial);
    }

    @GetMapping
    public ResponseEntity<List<CredencialCodigoBarraResponseDTO>> obterCredenciais() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<CredencialCodigoBarraResponseDTO> cadastrarCredencial(@RequestBody CredencialCodigoBarraRequestDTO dto) {
        CredencialCodigoBarraResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/credenciais-codigo-barra/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredencialCodigoBarraResponseDTO> atualizarCredencial(@PathVariable Long id, @RequestBody CredencialCodigoBarraRequestDTO dto) {
        CredencialCodigoBarraResponseDTO atualizado = servico.atualizar(id, dto);
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
