package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.MercadoriaRequestDTO;
import com.autobots.automanager.DTO.MercadoriaResponseDTO;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.repositorios.RepositorioMercadoria;

@Service
public class MercadoriaService {

    @Autowired
    private RepositorioMercadoria repositorio;

    public MercadoriaResponseDTO cadastrar(MercadoriaRequestDTO dto) {
        Mercadoria mercadoria = new Mercadoria();
        mercadoria.setValidade(dto.validade);
        mercadoria.setFabricao(dto.fabricao);
        mercadoria.setCadastro(dto.cadastro);
        mercadoria.setNome(dto.nome);
        if (dto.quantidade != null) {
            mercadoria.setQuantidade(dto.quantidade);
        }
        if (dto.valor != null) {
            mercadoria.setValor(dto.valor);
        }
        mercadoria.setDescricao(dto.descricao);

        mercadoria = repositorio.save(mercadoria);

        MercadoriaResponseDTO response = toResponse(mercadoria);
        response.add(org.springframework.hateoas.Link.of("/mercadorias/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/mercadorias").withRel("mercadorias"));
        return response;
    }

    public List<MercadoriaResponseDTO> listar() {
        List<MercadoriaResponseDTO> lista = repositorio.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        lista.forEach(mercadoria -> {
            mercadoria.add(org.springframework.hateoas.Link.of("/mercadorias/" + mercadoria.id).withSelfRel());
            mercadoria.add(org.springframework.hateoas.Link.of("/mercadorias").withRel("mercadorias"));
        });
        return lista;
    }

    public MercadoriaResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(mercadoria -> {
                    mercadoria.add(org.springframework.hateoas.Link.of("/mercadorias/" + mercadoria.id).withSelfRel());
                    mercadoria.add(org.springframework.hateoas.Link.of("/mercadorias").withRel("mercadorias"));
                    return mercadoria;
                })
                .orElse(null);
    }

    public MercadoriaResponseDTO atualizar(Long id, MercadoriaRequestDTO dto) {
        Mercadoria mercadoria = repositorio.findById(id).orElse(null);
        if (mercadoria == null) {
            return null;
        }
        if (dto.validade != null) {
            mercadoria.setValidade(dto.validade);
        }
        if (dto.fabricao != null) {
            mercadoria.setFabricao(dto.fabricao);
        }
        if (dto.cadastro != null) {
            mercadoria.setCadastro(dto.cadastro);
        }
        if (dto.nome != null) {
            mercadoria.setNome(dto.nome);
        }
        if (dto.quantidade != null) {
            mercadoria.setQuantidade(dto.quantidade);
        }
        if (dto.valor != null) {
            mercadoria.setValor(dto.valor);
        }
        if (dto.descricao != null) {
            mercadoria.setDescricao(dto.descricao);
        }

        mercadoria = repositorio.save(mercadoria);

        MercadoriaResponseDTO response = toResponse(mercadoria);
        response.add(org.springframework.hateoas.Link.of("/mercadorias/" + response.id).withSelfRel());
        response.add(org.springframework.hateoas.Link.of("/mercadorias").withRel("mercadorias"));
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private MercadoriaResponseDTO toResponse(Mercadoria mercadoria) {
        MercadoriaResponseDTO dto = new MercadoriaResponseDTO();
        dto.id = mercadoria.getId();
        dto.validade = mercadoria.getValidade();
        dto.fabricao = mercadoria.getFabricao();
        dto.cadastro = mercadoria.getCadastro();
        dto.nome = mercadoria.getNome();
        dto.quantidade = mercadoria.getQuantidade();
        dto.valor = mercadoria.getValor();
        dto.descricao = mercadoria.getDescricao();
        return dto;
    }
}