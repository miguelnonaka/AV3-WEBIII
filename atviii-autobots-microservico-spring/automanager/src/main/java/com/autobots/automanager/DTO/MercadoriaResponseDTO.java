package com.autobots.automanager.DTO;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class MercadoriaResponseDTO extends RepresentationModel<MercadoriaResponseDTO> {
    public Long id;
    public Date validade;
    public Date fabricao;
    public Date cadastro;
    public String nome;
    public Long quantidade;
    public double valor;
    public String descricao;
}