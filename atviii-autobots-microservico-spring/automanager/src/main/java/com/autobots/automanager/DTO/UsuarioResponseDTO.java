package com.autobots.automanager.DTO;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.entidades.Credencial;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Email;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.enumeracoes.PerfilUsuario;

public class UsuarioResponseDTO extends RepresentationModel<UsuarioResponseDTO> {
    public Long id;
    public String nome;
    public String nomeSocial;
    public Set<PerfilUsuario> perfis;
    public Set<Telefone> telefones;
    public Endereco endereco;
    public Set<Documento> documentos;
    public Set<Email> emails;
    public Set<Credencial> credenciais;
    public Long empresaId;
}