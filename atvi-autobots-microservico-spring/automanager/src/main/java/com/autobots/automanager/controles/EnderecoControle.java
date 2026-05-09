package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.servicos.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControle {

    @Autowired
    private EnderecoService servico;

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> obterEndereco(@PathVariable Long id) {
        Endereco endereco = servico.ListarByID(id);

        if (endereco == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(endereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> obterEnderecos() {
        return ResponseEntity.ok(servico.listar());
    }

	@PutMapping("/{id}")
	public ResponseEntity<Endereco> atualizarEndereco(
			@PathVariable Long id,
			@RequestBody Endereco atualizacao) {

		Endereco atualizado = servico.atualizar(id, atualizacao);

		if (atualizado == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(atualizado);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}