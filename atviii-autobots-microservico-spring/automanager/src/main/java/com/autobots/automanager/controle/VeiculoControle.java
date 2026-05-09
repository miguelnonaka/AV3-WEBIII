package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.VeiculoRequestDTO;
import com.autobots.automanager.DTO.VeiculoResponseDTO;
import com.autobots.automanager.servicos.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoControle {

    @Autowired
    private VeiculoService servico;

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> obterVeiculo(@PathVariable Long id) {
        VeiculoResponseDTO veiculo = servico.buscarPorId(id);
        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(veiculo);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> obterVeiculos() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> cadastrarVeiculo(@RequestBody VeiculoRequestDTO dto) {
        VeiculoResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/veiculos/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoRequestDTO dto) {
        VeiculoResponseDTO atualizado = servico.atualizar(id, dto);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}