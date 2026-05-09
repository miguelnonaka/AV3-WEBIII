package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.DTO.*;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.servicos.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteControle {

    @Autowired
    private ClienteService servico;

    @GetMapping("/{id}")
	public ResponseEntity<Cliente> obterCliente(@PathVariable Long id) {
		Cliente cliente = servico.buscarEntidadePorId(id);

		if (cliente == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(cliente);
}

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> obterClientes() {
        return ResponseEntity.ok(servico.listar());
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody ClienteRequestDTO dto) {

        ClienteResponseDTO resposta = servico.cadastrar(dto);

        return ResponseEntity
                .created(null)
                .body(resposta);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(
            @PathVariable Long id,
            @RequestBody ClienteRequestDTO dto) {

        ClienteResponseDTO atualizado = servico.atualizar(id, dto);

        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}