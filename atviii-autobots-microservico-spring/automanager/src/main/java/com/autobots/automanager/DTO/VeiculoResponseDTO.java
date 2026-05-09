package com.autobots.automanager.DTO;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumeracoes.TipoVeiculo;

public class VeiculoResponseDTO extends RepresentationModel<VeiculoResponseDTO> {
    public Long id;
    public TipoVeiculo tipo;
    public String modelo;
    public String placa;
    public Long proprietarioId;
    public Set<Long> vendaIds;
}