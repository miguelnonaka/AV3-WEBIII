package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.VendaRequestDTO;
import com.autobots.automanager.DTO.VendaResponseDTO;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import com.autobots.automanager.repositorios.RepositorioServico;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioVeiculo;
import com.autobots.automanager.repositorios.RepositorioVenda;

@Service
public class VendaService {

    @Autowired
    private RepositorioVenda repositorio;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioVeiculo repositorioVeiculo;

    @Autowired
    private RepositorioMercadoria repositorioMercadoria;

    @Autowired
    private RepositorioServico repositorioServico;

    public VendaResponseDTO cadastrar(VendaRequestDTO dto) {
        Venda venda = new Venda();
        venda.setCadastro(dto.cadastro);
        venda.setIdentificacao(dto.identificacao);

        if (dto.clienteId != null) {
            repositorioUsuario.findById(dto.clienteId).ifPresent(venda::setCliente);
        }
        if (dto.funcionarioId != null) {
            repositorioUsuario.findById(dto.funcionarioId).ifPresent(venda::setFuncionario);
        }
        if (dto.veiculoId != null) {
            repositorioVeiculo.findById(dto.veiculoId).ifPresent(venda::setVeiculo);
        }
        if (dto.mercadoriaIds != null) {
            dto.mercadoriaIds.stream()
                    .map(repositorioMercadoria::findById)
                    .filter(java.util.Optional::isPresent)
                    .map(java.util.Optional::get)
                    .forEach(venda.getMercadorias()::add);
        }
        if (dto.servicoIds != null) {
            dto.servicoIds.stream()
                    .map(repositorioServico::findById)
                    .filter(java.util.Optional::isPresent)
                    .map(java.util.Optional::get)
                    .forEach(venda.getServicos()::add);
        }

        venda = repositorio.save(venda);

        VendaResponseDTO response = toResponse(venda);
        response.add(org.springframework.hateoas.Link.of("/vendas/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/vendas").withRel("vendas"));
        return response;
    }

    public List<VendaResponseDTO> listar() {
        List<VendaResponseDTO> lista = repositorio.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        lista.forEach(venda -> {
            venda.add(org.springframework.hateoas.Link.of("/vendas/" + venda.id).withSelfRel());
            venda.add(org.springframework.hateoas.Link.of("/vendas").withRel("vendas"));
        });
        return lista;
    }

    public VendaResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(venda -> {
                    venda.add(org.springframework.hateoas.Link.of("/vendas/" + venda.id).withSelfRel());
                    venda.add(org.springframework.hateoas.Link.of("/vendas").withRel("vendas"));
                    return venda;
                })
                .orElse(null);
    }

    public VendaResponseDTO atualizar(Long id, VendaRequestDTO dto) {
        Venda venda = repositorio.findById(id).orElse(null);
        if (venda == null) {
            return null;
        }
        if (dto.cadastro != null) {
            venda.setCadastro(dto.cadastro);
        }
        if (dto.identificacao != null) {
            venda.setIdentificacao(dto.identificacao);
        }
        if (dto.clienteId != null) {
            repositorioUsuario.findById(dto.clienteId).ifPresent(venda::setCliente);
        }
        if (dto.funcionarioId != null) {
            repositorioUsuario.findById(dto.funcionarioId).ifPresent(venda::setFuncionario);
        }
        if (dto.veiculoId != null) {
            repositorioVeiculo.findById(dto.veiculoId).ifPresent(venda::setVeiculo);
        }
        if (dto.mercadoriaIds != null) {
            venda.getMercadorias().clear();
            dto.mercadoriaIds.stream()
                    .map(repositorioMercadoria::findById)
                    .filter(java.util.Optional::isPresent)
                    .map(java.util.Optional::get)
                    .forEach(venda.getMercadorias()::add);
        }
        if (dto.servicoIds != null) {
            venda.getServicos().clear();
            dto.servicoIds.stream()
                    .map(repositorioServico::findById)
                    .filter(java.util.Optional::isPresent)
                    .map(java.util.Optional::get)
                    .forEach(venda.getServicos()::add);
        }

        venda = repositorio.save(venda);

        VendaResponseDTO response = toResponse(venda);
        response.add(org.springframework.hateoas.Link.of("/vendas/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/vendas").withRel("vendas"));
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private VendaResponseDTO toResponse(Venda venda) {
        VendaResponseDTO dto = new VendaResponseDTO();
        dto.id = venda.getId();
        dto.cadastro = venda.getCadastro();
        dto.identificacao = venda.getIdentificacao();
        dto.clienteId = venda.getCliente() == null ? null : venda.getCliente().getId();
        dto.funcionarioId = venda.getFuncionario() == null ? null : venda.getFuncionario().getId();
        dto.veiculoId = venda.getVeiculo() == null ? null : venda.getVeiculo().getId();
        dto.mercadoriaIds = venda.getMercadorias().stream().map(Mercadoria::getId).collect(Collectors.toSet());
        dto.servicoIds = venda.getServicos().stream().map(Servico::getId).collect(Collectors.toSet());
        return dto;
    }
}