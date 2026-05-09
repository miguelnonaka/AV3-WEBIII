package com.autobots.automanager.DTO;

import java.util.Date;
import java.util.Set;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;

public class EmpresaRequestDTO {
    public String razaoSocial;
    public String nomeFantasia;
    public Set<Telefone> telefones;
    public Endereco endereco;
    public Date cadastro;
    public Set<Long> usuarioIds;
    public Set<Long> mercadoriaIds;
    public Set<Long> servicoIds;
    public Set<Long> vendaIds;
}