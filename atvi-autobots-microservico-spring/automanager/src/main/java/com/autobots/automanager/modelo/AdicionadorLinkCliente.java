package com.autobots.automanager.modelo;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.DTO.ClienteResponseDTO;
import com.autobots.automanager.controles.ClienteControle;

@Component
public class AdicionadorLinkCliente implements AdicionadorLink<ClienteResponseDTO> {

    @Override
    public void adicionarLink(List<ClienteResponseDTO> lista) {
        for (ClienteResponseDTO cliente : lista) {
            Link self = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class)
                    .obterCliente(cliente.id))
                    .withSelfRel();

            Link colecao = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class)
                    .obterClientes())
                    .withRel("clientes");

            cliente.add(self);
            cliente.add(colecao);
        }
    }

    @Override
    public void adicionarLink(ClienteResponseDTO objeto) {
        Link self = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class)
                .obterCliente(objeto.id))
                .withSelfRel();

        Link colecao = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class)
                .obterClientes())
                .withRel("clientes");

        objeto.add(self);
        objeto.add(colecao);
    }
}