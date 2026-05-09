package com.autobots.automanager.DTO;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class CredencialUsuarioSenhaResponseDTO extends RepresentationModel<CredencialUsuarioSenhaResponseDTO> {
    public Long id;
    public Date criacao;
    public Date ultimoAcesso;
    public boolean inativo;
    public String nomeUsuario;
}