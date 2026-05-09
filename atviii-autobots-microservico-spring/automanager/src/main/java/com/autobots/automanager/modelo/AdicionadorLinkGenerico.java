package com.autobots.automanager.modelo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

@Component
public class AdicionadorLinkGenerico {

    public void adicionarLink(RepresentationModel<?> recurso, String caminho, Long id) {
        if (recurso == null || caminho == null || id == null) {
            return;
        }

        recurso.add(Link.of("/" + caminho + "/" + id).withSelfRel());
        recurso.add(Link.of("/" + caminho).withRel(caminho));
    }
}
