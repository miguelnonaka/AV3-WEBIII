package com.autobots.automanager.DTO;

import java.util.Set;

import com.autobots.automanager.enumeracoes.TipoVeiculo;

public class VeiculoRequestDTO {
    public TipoVeiculo tipo;
    public String modelo;
    public String placa;
    public Long proprietarioId;
    public Set<Long> vendaIds;
}