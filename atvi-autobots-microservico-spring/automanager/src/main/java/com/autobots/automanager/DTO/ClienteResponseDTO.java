package com.autobots.automanager.DTO;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;

public class ClienteResponseDTO extends RepresentationModel<ClienteResponseDTO> {
    public Long id;
    public String nome;
    public String nomeSocial;
    public Date dataNascimento;
    public Date dataCadastro;

    public List<Telefone> telefones;
    public List<Documento> documentos;
    public Endereco endereco;
}