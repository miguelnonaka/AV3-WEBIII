package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.EmpresaRequestDTO;
import com.autobots.automanager.DTO.EmpresaResponseDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import com.autobots.automanager.repositorios.RepositorioServico;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioVenda;

@Service
public class EmpresaService {

    @Autowired
    private RepositorioEmpresa repositorio;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioMercadoria repositorioMercadoria;

    @Autowired
    private RepositorioServico repositorioServico;

    @Autowired
    private RepositorioVenda repositorioVenda;

    public EmpresaResponseDTO cadastrar(EmpresaRequestDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(dto.razaoSocial);
        empresa.setNomeFantasia(dto.nomeFantasia);
        empresa.setTelefones(dto.telefones);
        empresa.setEndereco(dto.endereco);
        empresa.setCadastro(dto.cadastro);

        carregarAssociacoes(empresa, dto);

        empresa = repositorio.save(empresa);

        EmpresaResponseDTO response = toResponse(empresa);
        response.add(org.springframework.hateoas.Link.of("/empresas/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/empresas").withRel("empresas"));
        return response;
    }

    public List<EmpresaResponseDTO> listar() {
        List<EmpresaResponseDTO> lista = repositorio.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        lista.forEach(empresa -> {
            empresa.add(org.springframework.hateoas.Link.of("/empresas/" + empresa.id).withSelfRel());
            empresa.add(org.springframework.hateoas.Link.of("/empresas").withRel("empresas"));
        });

        return lista;
    }

    public EmpresaResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(empresa -> {
                    empresa.add(org.springframework.hateoas.Link.of("/empresas/" + empresa.id).withSelfRel());
                    empresa.add(org.springframework.hateoas.Link.of("/empresas").withRel("empresas"));
                    return empresa;
                })
                .orElse(null);
    }

    public EmpresaResponseDTO atualizar(Long id, EmpresaRequestDTO dto) {
        Empresa empresa = repositorio.findById(id).orElse(null);
        if (empresa == null) {
            return null;
        }

        if (dto.razaoSocial != null) {
            empresa.setRazaoSocial(dto.razaoSocial);
        }
        if (dto.nomeFantasia != null) {
            empresa.setNomeFantasia(dto.nomeFantasia);
        }
        if (dto.telefones != null) {
            empresa.setTelefones(dto.telefones);
        }
        if (dto.endereco != null) {
            empresa.setEndereco(dto.endereco);
        }
        if (dto.cadastro != null) {
            empresa.setCadastro(dto.cadastro);
        }
        if (dto.usuarioIds != null || dto.mercadoriaIds != null || dto.servicoIds != null || dto.vendaIds != null) {
            carregarAssociacoes(empresa, dto);
        }

        empresa = repositorio.save(empresa);

        EmpresaResponseDTO response = toResponse(empresa);
        response.add(org.springframework.hateoas.Link.of("/empresas/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/empresas").withRel("empresas"));
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private void carregarAssociacoes(Empresa empresa, EmpresaRequestDTO dto) {
        if (dto.usuarioIds != null) {
            empresa.getUsuarios().clear();
            dto.usuarioIds.stream()
                    .map(repositorioUsuario::findById)
                    .filter(java.util.Optional::isPresent)
                    .map(java.util.Optional::get)
                    .forEach(empresa.getUsuarios()::add);
        }
        if (dto.mercadoriaIds != null) {
            empresa.getMercadorias().clear();
            dto.mercadoriaIds.stream()
                    .map(repositorioMercadoria::findById)
                    .filter(java.util.Optional::isPresent)
                    .map(java.util.Optional::get)
                    .forEach(empresa.getMercadorias()::add);
        }
        if (dto.servicoIds != null) {
            empresa.getServicos().clear();
            dto.servicoIds.stream()
                    .map(repositorioServico::findById)
                    .filter(java.util.Optional::isPresent)
                    .map(java.util.Optional::get)
                    .forEach(empresa.getServicos()::add);
        }
        if (dto.vendaIds != null) {
            empresa.getVendas().clear();
            dto.vendaIds.stream()
                    .map(repositorioVenda::findById)
                    .filter(java.util.Optional::isPresent)
                    .map(java.util.Optional::get)
                    .forEach(empresa.getVendas()::add);
        }
    }

    private EmpresaResponseDTO toResponse(Empresa empresa) {
        EmpresaResponseDTO dto = new EmpresaResponseDTO();
        dto.id = empresa.getId();
        dto.razaoSocial = empresa.getRazaoSocial();
        dto.nomeFantasia = empresa.getNomeFantasia();
        dto.telefones = empresa.getTelefones();
        dto.endereco = empresa.getEndereco();
        dto.cadastro = empresa.getCadastro();
        dto.usuarioIds = empresa.getUsuarios().stream().map(Usuario::getId).collect(Collectors.toSet());
        dto.mercadoriaIds = empresa.getMercadorias().stream().map(Mercadoria::getId).collect(Collectors.toSet());
        dto.servicoIds = empresa.getServicos().stream().map(Servico::getId).collect(Collectors.toSet());
        dto.vendaIds = empresa.getVendas().stream().map(Venda::getId).collect(Collectors.toSet());
        return dto;
    }
}