package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.VendaRequestDTO;
import com.autobots.automanager.DTO.VendaResponseDTO;
import com.autobots.automanager.servicos.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaControle {

    @Autowired
    private VendaService servico;

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> obterVenda(@PathVariable Long id) {
        VendaResponseDTO venda = servico.buscarPorId(id);
        if (venda == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venda);
    }

    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> obterVendas() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<VendaResponseDTO> cadastrarVenda(@RequestBody VendaRequestDTO dto) {
        VendaResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/vendas/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> atualizarVenda(@PathVariable Long id, @RequestBody VendaRequestDTO dto) {
        VendaResponseDTO atualizado = servico.atualizar(id, dto);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVenda(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}