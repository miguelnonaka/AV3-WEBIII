package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.MercadoriaRequestDTO;
import com.autobots.automanager.DTO.MercadoriaResponseDTO;
import com.autobots.automanager.servicos.MercadoriaService;

@RestController
@RequestMapping("/mercadorias")
public class MercadoriaControle {

    @Autowired
    private MercadoriaService servico;

    @GetMapping("/{id}")
    public ResponseEntity<MercadoriaResponseDTO> obterMercadoria(@PathVariable Long id) {
        MercadoriaResponseDTO mercadoria = servico.buscarPorId(id);
        if (mercadoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mercadoria);
    }

    @GetMapping
    public ResponseEntity<List<MercadoriaResponseDTO>> obterMercadorias() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<MercadoriaResponseDTO> cadastrarMercadoria(@RequestBody MercadoriaRequestDTO dto) {
        MercadoriaResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/mercadorias/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MercadoriaResponseDTO> atualizarMercadoria(@PathVariable Long id, @RequestBody MercadoriaRequestDTO dto) {
        MercadoriaResponseDTO atualizado = servico.atualizar(id, dto);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMercadoria(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}