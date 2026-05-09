package com.autobots.automanager.DTO;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class CredencialCodigoBarraResponseDTO extends RepresentationModel<CredencialCodigoBarraResponseDTO> {
    public Long id;
    public Date criacao;
    public Date ultimoAcesso;
    public boolean inativo;
    public Long codigo;
}
