package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.EnderecoRequestDTO;
import com.autobots.automanager.DTO.EnderecoResponseDTO;
import com.autobots.automanager.servicos.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControle {

    @Autowired
    private EnderecoService servico;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> obterEndereco(@PathVariable Long id) {
        EnderecoResponseDTO endereco = servico.buscarPorId(id);
        if (endereco == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(endereco);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> obterEnderecos() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> cadastrarEndereco(@RequestBody EnderecoRequestDTO dto) {
        EnderecoResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/enderecos/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoRequestDTO dto) {
        EnderecoResponseDTO atualizado = servico.atualizar(id, dto);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
