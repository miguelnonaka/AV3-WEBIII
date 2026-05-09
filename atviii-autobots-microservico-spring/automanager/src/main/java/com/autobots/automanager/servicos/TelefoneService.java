package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.TelefoneRequestDTO;
import com.autobots.automanager.DTO.TelefoneResponseDTO;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.AdicionadorLinkGenerico;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepositorio repositorio;

    @Autowired
    private AdicionadorLinkGenerico adicionador;

    public TelefoneResponseDTO cadastrar(TelefoneRequestDTO dto) {
        Telefone telefone = new Telefone();
        telefone.setDdd(dto.ddd);
        telefone.setNumero(dto.numero);

        telefone = repositorio.save(telefone);

        TelefoneResponseDTO response = toResponse(telefone);
        adicionador.adicionarLink(response, "telefones", response.id);
        return response;
    }

    public List<TelefoneResponseDTO> listar() {
        List<TelefoneResponseDTO> lista = repositorio.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        lista.forEach(telefone -> adicionador.adicionarLink(telefone, "telefones", telefone.id));
        return lista;
    }

    public TelefoneResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(telefone -> {
                    adicionador.adicionarLink(telefone, "telefones", telefone.id);
                    return telefone;
                })
                .orElse(null);
    }

    public TelefoneResponseDTO atualizar(Long id, TelefoneRequestDTO dto) {
        Telefone telefone = repositorio.findById(id).orElse(null);
        if (telefone == null) {
            return null;
        }
        if (dto.ddd != null) {
            telefone.setDdd(dto.ddd);
        }
        if (dto.numero != null) {
            telefone.setNumero(dto.numero);
        }

        telefone = repositorio.save(telefone);
        TelefoneResponseDTO response = toResponse(telefone);
        adicionador.adicionarLink(response, "telefones", response.id);
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private TelefoneResponseDTO toResponse(Telefone telefone) {
        TelefoneResponseDTO dto = new TelefoneResponseDTO();
        dto.id = telefone.getId();
        dto.ddd = telefone.getDdd();
        dto.numero = telefone.getNumero();
        return dto;
    }
}
