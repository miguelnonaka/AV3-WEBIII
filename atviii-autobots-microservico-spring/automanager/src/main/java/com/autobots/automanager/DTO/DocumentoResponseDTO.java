package com.autobots.automanager.DTO;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumeracoes.TipoDocumento;

public class DocumentoResponseDTO extends RepresentationModel<DocumentoResponseDTO> {
    public Long id;
    public TipoDocumento tipo;
    public Date dataEmissao;
    public String numero;
}
