package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.CredencialCodigoBarraRequestDTO;
import com.autobots.automanager.DTO.CredencialCodigoBarraResponseDTO;
import com.autobots.automanager.entidades.CredencialCodigoBarra;
import com.autobots.automanager.modelo.AdicionadorLinkGenerico;
import com.autobots.automanager.repositorios.RepositorioCredencialBarra;

@Service
public class CredencialCodigoBarraService {

    @Autowired
    private RepositorioCredencialBarra repositorio;

    @Autowired
    private AdicionadorLinkGenerico adicionador;

    public CredencialCodigoBarraResponseDTO cadastrar(CredencialCodigoBarraRequestDTO dto) {
        CredencialCodigoBarra credencial = new CredencialCodigoBarra();
        credencial.setCriacao(dto.criacao);
        credencial.setUltimoAcesso(dto.ultimoAcesso);
        credencial.setInativo(dto.inativo != null ? dto.inativo : false);
        credencial.setCodigo(dto.codigo);

        credencial = repositorio.save(credencial);

        CredencialCodigoBarraResponseDTO response = toResponse(credencial);
        adicionador.adicionarLink(response, "credenciais-codigo-barra", response.id);
        return response;
    }

    public List<CredencialCodigoBarraResponseDTO> listar() {
        List<CredencialCodigoBarraResponseDTO> lista = repositorio.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        lista.forEach(credencial -> adicionador.adicionarLink(credencial, "credenciais-codigo-barra", credencial.id));
        return lista;
    }

    public CredencialCodigoBarraResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(credencial -> {
                    adicionador.adicionarLink(credencial, "credenciais-codigo-barra", credencial.id);
                    return credencial;
                })
                .orElse(null);
    }

    public CredencialCodigoBarraResponseDTO atualizar(Long id, CredencialCodigoBarraRequestDTO dto) {
        CredencialCodigoBarra credencial = repositorio.findById(id).orElse(null);
        if (credencial == null) {
            return null;
        }
        if (dto.criacao != null) {
            credencial.setCriacao(dto.criacao);
        }
        if (dto.ultimoAcesso != null) {
            credencial.setUltimoAcesso(dto.ultimoAcesso);
        }
        if (dto.inativo != null) {
            credencial.setInativo(dto.inativo);
        }
        if (dto.codigo != null) {
            credencial.setCodigo(dto.codigo);
        }

        credencial = repositorio.save(credencial);
        CredencialCodigoBarraResponseDTO response = toResponse(credencial);
        adicionador.adicionarLink(response, "credenciais-codigo-barra", response.id);
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private CredencialCodigoBarraResponseDTO toResponse(CredencialCodigoBarra credencial) {
        CredencialCodigoBarraResponseDTO dto = new CredencialCodigoBarraResponseDTO();
        dto.id = credencial.getId();
        dto.criacao = credencial.getCriacao();
        dto.ultimoAcesso = credencial.getUltimoAcesso();
        dto.inativo = credencial.isInativo();
        dto.codigo = credencial.getCodigo();
        return dto;
    }
}
