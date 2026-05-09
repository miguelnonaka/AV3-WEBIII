package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.VeiculoRequestDTO;
import com.autobots.automanager.DTO.VeiculoResponseDTO;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioVeiculo;
import com.autobots.automanager.repositorios.RepositorioVenda;

@Service
public class VeiculoService {

    @Autowired
    private RepositorioVeiculo repositorio;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioVenda repositorioVenda;

    public VeiculoResponseDTO cadastrar(VeiculoRequestDTO dto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setTipo(dto.tipo);
        veiculo.setModelo(dto.modelo);
        veiculo.setPlaca(dto.placa);
        if (dto.proprietarioId != null) {
            repositorioUsuario.findById(dto.proprietarioId).ifPresent(veiculo::setProprietario);
        }
        if (dto.vendaIds != null) {
            dto.vendaIds.stream()
                    .map(repositorioVenda::findById)
                    .filter(java.util.Optional::isPresent)
                    .map(java.util.Optional::get)
                    .forEach(veiculo.getVendas()::add);
        }

        veiculo = repositorio.save(veiculo);

        VeiculoResponseDTO response = toResponse(veiculo);
        response.add(org.springframework.hateoas.Link.of("/veiculos/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/veiculos").withRel("veiculos"));
        return response;
    }

    public List<VeiculoResponseDTO> listar() {
        List<VeiculoResponseDTO> lista = repositorio.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        lista.forEach(veiculo -> {
            veiculo.add(org.springframework.hateoas.Link.of("/veiculos/" + veiculo.id).withSelfRel());
            veiculo.add(org.springframework.hateoas.Link.of("/veiculos").withRel("veiculos"));
        });
        return lista;
    }

    public VeiculoResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(veiculo -> {
                    veiculo.add(org.springframework.hateoas.Link.of("/veiculos/" + veiculo.id).withSelfRel());
                    veiculo.add(org.springframework.hateoas.Link.of("/veiculos").withRel("veiculos"));
                    return veiculo;
                })
                .orElse(null);
    }

    public VeiculoResponseDTO atualizar(Long id, VeiculoRequestDTO dto) {
        Veiculo veiculo = repositorio.findById(id).orElse(null);
        if (veiculo == null) {
            return null;
        }
        if (dto.tipo != null) {
            veiculo.setTipo(dto.tipo);
        }
        if (dto.modelo != null) {
            veiculo.setModelo(dto.modelo);
        }
        if (dto.placa != null) {
            veiculo.setPlaca(dto.placa);
        }
        if (dto.proprietarioId != null) {
            repositorioUsuario.findById(dto.proprietarioId).ifPresent(veiculo::setProprietario);
        }
        if (dto.vendaIds != null) {
            veiculo.getVendas().clear();
            dto.vendaIds.stream()
                    .map(repositorioVenda::findById)
                    .filter(java.util.Optional::isPresent)
                    .map(java.util.Optional::get)
                    .forEach(veiculo.getVendas()::add);
        }

        veiculo = repositorio.save(veiculo);

        VeiculoResponseDTO response = toResponse(veiculo);
        response.add(org.springframework.hateoas.Link.of("/veiculos/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/veiculos").withRel("veiculos"));
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private VeiculoResponseDTO toResponse(Veiculo veiculo) {
        VeiculoResponseDTO dto = new VeiculoResponseDTO();
        dto.id = veiculo.getId();
        dto.tipo = veiculo.getTipo();
        dto.modelo = veiculo.getModelo();
        dto.placa = veiculo.getPlaca();
        dto.proprietarioId = veiculo.getProprietario() == null ? null : veiculo.getProprietario().getId();
        dto.vendaIds = veiculo.getVendas().stream().map(Venda::getId).collect(Collectors.toSet());
        return dto;
    }
}