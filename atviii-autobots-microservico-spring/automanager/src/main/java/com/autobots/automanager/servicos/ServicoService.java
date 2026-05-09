package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.ServicoRequestDTO;
import com.autobots.automanager.DTO.ServicoResponseDTO;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.repositorios.RepositorioServico;

@Service
public class ServicoService {

    @Autowired
    private RepositorioServico repositorio;

    public ServicoResponseDTO cadastrar(ServicoRequestDTO dto) {
        Servico servico = new Servico();
        servico.setNome(dto.nome);
        if (dto.valor != null) {
            servico.setValor(dto.valor);
        }
        servico.setDescricao(dto.descricao);

        servico = repositorio.save(servico);

        ServicoResponseDTO response = toResponse(servico);
        response.add(org.springframework.hateoas.Link.of("/servicos/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/servicos").withRel("servicos"));
        return response;
    }

    public List<ServicoResponseDTO> listar() {
        List<ServicoResponseDTO> lista = repositorio.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        lista.forEach(servico -> {
            servico.add(org.springframework.hateoas.Link.of("/servicos/" + servico.id).withSelfRel());
            servico.add(org.springframework.hateoas.Link.of("/servicos").withRel("servicos"));
        });
        return lista;
    }

    public ServicoResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(servico -> {
                    servico.add(org.springframework.hateoas.Link.of("/servicos/" + servico.id).withSelfRel());
                    servico.add(org.springframework.hateoas.Link.of("/servicos").withRel("servicos"));
                    return servico;
                })
                .orElse(null);
    }

    public ServicoResponseDTO atualizar(Long id, ServicoRequestDTO dto) {
        Servico servico = repositorio.findById(id).orElse(null);
        if (servico == null) {
            return null;
        }
        if (dto.nome != null) {
            servico.setNome(dto.nome);
        }
        if (dto.valor != null) {
            servico.setValor(dto.valor);
        }
        if (dto.descricao != null) {
            servico.setDescricao(dto.descricao);
        }

        servico = repositorio.save(servico);

        ServicoResponseDTO response = toResponse(servico);
        response.add(org.springframework.hateoas.Link.of("/servicos/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/servicos").withRel("servicos"));
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private ServicoResponseDTO toResponse(Servico servico) {
        ServicoResponseDTO dto = new ServicoResponseDTO();
        dto.id = servico.getId();
        dto.nome = servico.getNome();
        dto.valor = servico.getValor();
        dto.descricao = servico.getDescricao();
        return dto;
    }
}