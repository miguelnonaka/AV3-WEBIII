package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.servicos.DocumentoService;

@RestController

@RequestMapping("/documento")
public class DocumentoControle {
    @Autowired
	private DocumentoService servico;

	@GetMapping("/{id}")
	public ResponseEntity<Documento> obterDocumento(@PathVariable Long id) {
		Documento doc = servico.ListarByID(id);

		if (doc == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(doc);
	}

	@GetMapping
	public List<Documento> obterDocumentos() {
		List<Documento> documentos = servico.listar();
		return documentos;
	}

	@PostMapping
	public ResponseEntity<String> cadastrarDocumento(@RequestBody Documento cliente) {
		servico.cadastrar(cliente);
		return ResponseEntity.status(201).body("Documento cadastrado");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Documento> atualizarDocumento(
			@PathVariable Long id,
			@RequestBody Documento atualizacao) {

		Documento atualizado = servico.atualizar(id, atualizacao);

		if (atualizado == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluirDocumento(@PathVariable Long id) {
		servico.deletar(id);
		ResponseEntity<String> resposta = new ResponseEntity<>("Documento deletado", HttpStatus.OK);
		return resposta;
	}
}
