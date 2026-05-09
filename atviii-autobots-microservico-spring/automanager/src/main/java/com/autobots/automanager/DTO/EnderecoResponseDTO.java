package com.autobots.automanager.DTO;

import org.springframework.hateoas.RepresentationModel;

public class EnderecoResponseDTO extends RepresentationModel<EnderecoResponseDTO> {
    public Long id;
    public String estado;
    public String cidade;
    public String bairro;
    public String rua;
    public String numero;
    public String codigoPostal;
    public String informacoesAdicionais;
}
