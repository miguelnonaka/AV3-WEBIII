package com.autobots.automanager.DTO;

import org.springframework.hateoas.RepresentationModel;

public class TelefoneResponseDTO extends RepresentationModel<TelefoneResponseDTO> {
    public Long id;
    public String ddd;
    public String numero;
}
