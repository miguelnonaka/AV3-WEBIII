package com.autobots.automanager.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.ClienteRequestDTO;
import com.autobots.automanager.DTO.ClienteResponseDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelo.AdicionadorLinkCliente;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositorio repositorio;

    @Autowired
    private AdicionadorLinkCliente adicionador;

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome);
        cliente.setNomeSocial(dto.nomeSocial);
        cliente.setDataNascimento(dto.dataNascimento);

        cliente = repositorio.save(cliente);

        ClienteResponseDTO response = toResponse(cliente);
        adicionador.adicionarLink(response);
        return response;
    }

    public List<ClienteResponseDTO> listar() {
        List<ClienteResponseDTO> lista = repositorio.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

        adicionador.adicionarLink(lista);
        return lista;
    }

    public Cliente buscarEntidadePorId(Long id){
        return repositorio.findById(id).orElse(null);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repositorio.findById(id).orElse(null);
        if (cliente == null) return null;

        if (dto.nome != null) {
            cliente.setNome(dto.nome);
        }
        if (dto.nomeSocial != null) {
            cliente.setNomeSocial(dto.nomeSocial);
        }
        if (dto.dataNascimento != null) {
            cliente.setDataNascimento(dto.dataNascimento);
        }

        cliente = repositorio.save(cliente);

        ClienteResponseDTO response = toResponse(cliente);
        adicionador.adicionarLink(response);
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private ClienteResponseDTO toResponse(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();

        dto.id = cliente.getId();
        dto.nome = cliente.getNome();
        dto.nomeSocial = cliente.getNomeSocial();
        dto.dataNascimento = cliente.getDataNascimento();
        dto.dataCadastro = cliente.getDataCadastro();

        dto.telefones = cliente.getTelefones();
        dto.documentos = cliente.getDocumentos();
        dto.endereco = cliente.getEndereco();

        return dto;
    }
}