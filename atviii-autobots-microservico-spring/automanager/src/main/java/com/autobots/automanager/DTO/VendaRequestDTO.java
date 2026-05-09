package com.autobots.automanager.DTO;

import java.util.Date;
import java.util.Set;

public class VendaRequestDTO {
    public Date cadastro;
    public String identificacao;
    public Long clienteId;
    public Long funcionarioId;
    public Long veiculoId;
    public Set<Long> mercadoriaIds;
    public Set<Long> servicoIds;
}