package com.autobots.automanager.DTO;

import org.springframework.hateoas.RepresentationModel;

public class EmailResponseDTO extends RepresentationModel<EmailResponseDTO> {
    public Long id;
    public String endereco;
}
