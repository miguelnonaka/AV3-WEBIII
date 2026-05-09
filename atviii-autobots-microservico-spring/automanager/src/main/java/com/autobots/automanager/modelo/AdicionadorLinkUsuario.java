package com.autobots.automanager.modelo;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.autobots.automanager.DTO.UsuarioResponseDTO;

@Component
public class AdicionadorLinkUsuario {

    public void adicionarLink(UsuarioResponseDTO usuario) {
        if (usuario == null || usuario.id == null) return;

        usuario.add(Link.of("/usuarios/" + usuario.id).withSelfRel());
        usuario.add(Link.of("/usuarios").withRel("usuarios"));
    }

    public void adicionarLink(List<UsuarioResponseDTO> usuarios) {
        for (UsuarioResponseDTO usuario : usuarios) {
            adicionarLink(usuario);
        }
    }
}