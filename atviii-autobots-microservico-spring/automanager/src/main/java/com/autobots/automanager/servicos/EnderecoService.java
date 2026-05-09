package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.EnderecoRequestDTO;
import com.autobots.automanager.DTO.EnderecoResponseDTO;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.AdicionadorLinkGenerico;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepositorio repositorio;

    @Autowired
    private AdicionadorLinkGenerico adicionador;

    public EnderecoResponseDTO cadastrar(EnderecoRequestDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setEstado(dto.estado);
        endereco.setCidade(dto.cidade);
        endereco.setBairro(dto.bairro);
        endereco.setRua(dto.rua);
        endereco.setNumero(dto.numero);
        endereco.setCodigoPostal(dto.codigoPostal);
        endereco.setInformacoesAdicionais(dto.informacoesAdicionais);

        endereco = repositorio.save(endereco);

        EnderecoResponseDTO response = toResponse(endereco);
        adicionador.adicionarLink(response, "enderecos", response.id);
        return response;
    }

    public List<EnderecoResponseDTO> listar() {
        List<EnderecoResponseDTO> lista = repositorio.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        lista.forEach(endereco -> adicionador.adicionarLink(endereco, "enderecos", endereco.id));
        return lista;
    }

    public EnderecoResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(endereco -> {
                    adicionador.adicionarLink(endereco, "enderecos", endereco.id);
                    return endereco;
                })
                .orElse(null);
    }

    public EnderecoResponseDTO atualizar(Long id, EnderecoRequestDTO dto) {
        Endereco endereco = repositorio.findById(id).orElse(null);
        if (endereco == null) {
            return null;
        }
        if (dto.estado != null) {
            endereco.setEstado(dto.estado);
        }
        if (dto.cidade != null) {
            endereco.setCidade(dto.cidade);
        }
        if (dto.bairro != null) {
            endereco.setBairro(dto.bairro);
        }
        if (dto.rua != null) {
            endereco.setRua(dto.rua);
        }
        if (dto.numero != null) {
            endereco.setNumero(dto.numero);
        }
        if (dto.codigoPostal != null) {
            endereco.setCodigoPostal(dto.codigoPostal);
        }
        if (dto.informacoesAdicionais != null) {
            endereco.setInformacoesAdicionais(dto.informacoesAdicionais);
        }

        endereco = repositorio.save(endereco);
        EnderecoResponseDTO response = toResponse(endereco);
        adicionador.adicionarLink(response, "enderecos", response.id);
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private EnderecoResponseDTO toResponse(Endereco endereco) {
        EnderecoResponseDTO dto = new EnderecoResponseDTO();
        dto.id = endereco.getId();
        dto.estado = endereco.getEstado();
        dto.cidade = endereco.getCidade();
        dto.bairro = endereco.getBairro();
        dto.rua = endereco.getRua();
        dto.numero = endereco.getNumero();
        dto.codigoPostal = endereco.getCodigoPostal();
        dto.informacoesAdicionais = endereco.getInformacoesAdicionais();
        return dto;
    }
}
