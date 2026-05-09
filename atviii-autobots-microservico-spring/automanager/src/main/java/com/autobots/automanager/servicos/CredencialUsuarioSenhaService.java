package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.CredencialUsuarioSenhaRequestDTO;
import com.autobots.automanager.DTO.CredencialUsuarioSenhaResponseDTO;
import com.autobots.automanager.entidades.CredencialUsuarioSenha;
import com.autobots.automanager.repositorios.RepositorioCredencialUsuarioSenha;

@Service
public class CredencialUsuarioSenhaService {

    @Autowired
    private RepositorioCredencialUsuarioSenha repositorio;

    public CredencialUsuarioSenhaResponseDTO cadastrar(CredencialUsuarioSenhaRequestDTO dto) {
        CredencialUsuarioSenha credencial = new CredencialUsuarioSenha();
        credencial.setCriacao(dto.criacao);
        credencial.setUltimoAcesso(dto.ultimoAcesso);
        credencial.setInativo(dto.inativo != null ? dto.inativo : false);
        credencial.setNomeUsuario(dto.nomeUsuario);
        credencial.setSenha(dto.senha);

        credencial = repositorio.save(credencial);

        CredencialUsuarioSenhaResponseDTO response = toResponse(credencial);
        response.add(org.springframework.hateoas.Link.of("/credenciais-senha/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/credenciais-senha").withRel("credenciais-senha"));
        return response;
    }

    public List<CredencialUsuarioSenhaResponseDTO> listar() {
        List<CredencialUsuarioSenhaResponseDTO> lista = repositorio.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        lista.forEach(credencial -> {
            credencial.add(org.springframework.hateoas.Link.of("/credenciais-senha/" + credencial.id).withSelfRel());
            credencial.add(org.springframework.hateoas.Link.of("/credenciais-senha").withRel("credenciais-senha"));
        });
        return lista;
    }

    public CredencialUsuarioSenhaResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(credencial -> {
                    credencial.add(org.springframework.hateoas.Link.of("/credenciais-senha/" + credencial.id).withSelfRel());
                    credencial.add(org.springframework.hateoas.Link.of("/credenciais-senha").withRel("credenciais-senha"));
                    return credencial;
                })
                .orElse(null);
    }

    public CredencialUsuarioSenhaResponseDTO atualizar(Long id, CredencialUsuarioSenhaRequestDTO dto) {
        CredencialUsuarioSenha credencial = repositorio.findById(id).orElse(null);
        if (credencial == null) {
            return null;
        }
        if (dto.criacao != null) {
            credencial.setCriacao(dto.criacao);
        }
        if (dto.ultimoAcesso != null) {
            credencial.setUltimoAcesso(dto.ultimoAcesso);
        }
        if (dto.inativo != null) {
            credencial.setInativo(dto.inativo);
        }
        if (dto.nomeUsuario != null) {
            credencial.setNomeUsuario(dto.nomeUsuario);
        }
        if (dto.senha != null) {
            credencial.setSenha(dto.senha);
        }

        credencial = repositorio.save(credencial);

        CredencialUsuarioSenhaResponseDTO response = toResponse(credencial);
        response.add(org.springframework.hateoas.Link.of("/credenciais-senha/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/credenciais-senha").withRel("credenciais-senha"));
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private CredencialUsuarioSenhaResponseDTO toResponse(CredencialUsuarioSenha credencial) {
        CredencialUsuarioSenhaResponseDTO dto = new CredencialUsuarioSenhaResponseDTO();
        dto.id = credencial.getId();
        dto.criacao = credencial.getCriacao();
        dto.ultimoAcesso = credencial.getUltimoAcesso();
        dto.inativo = credencial.isInativo();
        dto.nomeUsuario = credencial.getNomeUsuario();
        return dto;
    }
}