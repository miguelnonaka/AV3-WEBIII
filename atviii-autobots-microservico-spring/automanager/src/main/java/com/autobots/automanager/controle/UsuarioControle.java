package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.UsuarioRequestDTO;
import com.autobots.automanager.DTO.UsuarioResponseDTO;
import com.autobots.automanager.servicos.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControle {

    @Autowired
    private UsuarioService servico;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obterUsuario(@PathVariable Long id) {

        UsuarioResponseDTO usuario = servico.buscarPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obterUsuarios() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(
            @RequestBody UsuarioRequestDTO dto) {

        UsuarioResponseDTO resposta = servico.cadastrar(dto);

        return ResponseEntity
                .created(java.net.URI.create("/usuarios/" + resposta.id))
                .body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioRequestDTO dto) {

        UsuarioResponseDTO atualizado = servico.atualizar(id, dto);

        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {

        servico.deletar(id);

        return ResponseEntity.noContent().build();
    }
}