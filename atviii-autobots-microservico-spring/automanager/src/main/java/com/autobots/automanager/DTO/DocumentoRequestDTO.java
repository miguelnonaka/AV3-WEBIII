package com.autobots.automanager.DTO;

import java.util.Date;

import com.autobots.automanager.enumeracoes.TipoDocumento;

public class DocumentoRequestDTO {
    public TipoDocumento tipo;
    public Date dataEmissao;
    public String numero;
}
