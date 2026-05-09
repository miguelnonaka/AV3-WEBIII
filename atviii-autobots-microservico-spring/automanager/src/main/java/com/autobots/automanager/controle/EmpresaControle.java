package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.EmpresaRequestDTO;
import com.autobots.automanager.DTO.EmpresaResponseDTO;
import com.autobots.automanager.servicos.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaControle {

    @Autowired
    private EmpresaService servico;

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> obterEmpresa(@PathVariable Long id) {
        EmpresaResponseDTO empresa = servico.buscarPorId(id);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> obterEmpresas() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> cadastrarEmpresa(@RequestBody EmpresaRequestDTO dto) {
        EmpresaResponseDTO resposta = servico.cadastrar(dto);
        return ResponseEntity.created(java.net.URI.create("/empresas/" + resposta.id)).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> atualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaRequestDTO dto) {
        EmpresaResponseDTO atualizado = servico.atualizar(id, dto);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEmpresa(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}