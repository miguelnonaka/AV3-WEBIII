package com.autobots.automanager.DTO;

import java.util.Date;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

public class VendaResponseDTO extends RepresentationModel<VendaResponseDTO> {
    public Long id;
    public Date cadastro;
    public String identificacao;
    public Long clienteId;
    public Long funcionarioId;
    public Long veiculoId;
    public Set<Long> mercadoriaIds;
    public Set<Long> servicoIds;
}