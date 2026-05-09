package com.autobots.automanager.DTO;

import org.springframework.hateoas.RepresentationModel;

public class ServicoResponseDTO extends RepresentationModel<ServicoResponseDTO> {
    public Long id;
    public String nome;
    public double valor;
    public String descricao;
}