package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.servicos.TelefoneService;

@RestController
@RequestMapping("/telefones")
public class TelefoneControle {

    @Autowired
    private TelefoneService servico;

	@GetMapping("/{id}")
	public ResponseEntity<Telefone> obterTelefone(@PathVariable Long id) {
		Telefone telefone = servico.ListarByID(id);

		if (telefone == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(telefone);
	}

    @GetMapping
    public ResponseEntity<List<Telefone>> obterTelefones() {
        return ResponseEntity.ok(servico.listar());
    }

	@PutMapping("/{id}")
	public ResponseEntity<Telefone> atualizarTelefone(
			@PathVariable Long id,
			@RequestBody Telefone atualizacao) {

		Telefone atualizado = servico.atualizar(id, atualizacao);

		if (atualizado == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(atualizado);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTelefone(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}