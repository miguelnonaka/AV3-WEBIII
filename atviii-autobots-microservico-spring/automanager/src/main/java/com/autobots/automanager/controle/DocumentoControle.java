package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.DocumentoRequestDTO;
import com.autobots.automanager.DTO.DocumentoResponseDTO;
import com.autobots.automanager.servicos.DocumentoService;

@RestController
@RequestMapping("/documentos")
public class DocumentoControle {

    @Autowired
    private DocumentoService servico;

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> obterDocumento(@PathVariable Long id) {
        DocumentoResponseDTO resposta = servico.buscarPorId(id);

        if (resposta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<DocumentoResponseDTO>> obterDocumentos() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<DocumentoResponseDTO> cadastrarDocumento(@RequestBody DocumentoRequestDTO dto) {
        DocumentoResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/documentos/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> atualizarDocumento(
            @PathVariable Long id,
            @RequestBody DocumentoRequestDTO dto) {

        DocumentoResponseDTO atualizado = servico.atualizar(id, dto);

        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDocumento(@PathVariable Long id) {
        boolean removido = servico.deletar(id);

        if (!removido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}