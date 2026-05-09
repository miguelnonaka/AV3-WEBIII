package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.DocumentoRequestDTO;
import com.autobots.automanager.DTO.DocumentoResponseDTO;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.AdicionadorLinkGenerico;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepositorio repositorio;

    @Autowired
    private AdicionadorLinkGenerico adicionador;

    public DocumentoResponseDTO cadastrar(DocumentoRequestDTO dto) {
        Documento documento = new Documento();
        documento.setTipo(dto.tipo);
        documento.setDataEmissao(dto.dataEmissao);
        documento.setNumero(dto.numero);

        documento = repositorio.save(documento);

        DocumentoResponseDTO response = toResponse(documento);
        adicionador.adicionarLink(response, "documentos", response.id);
        return response;
    }

    public List<DocumentoResponseDTO> listar() {
        List<DocumentoResponseDTO> lista = repositorio.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        lista.forEach(documento -> adicionador.adicionarLink(documento, "documentos", documento.id));
        return lista;
    }

    public DocumentoResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(documento -> {
                    adicionador.adicionarLink(documento, "documentos", documento.id);
                    return documento;
                })
                .orElse(null);
    }

    public DocumentoResponseDTO atualizar(Long id, DocumentoRequestDTO dto) {
        Documento documento = repositorio.findById(id).orElse(null);
        if (documento == null) {
            return null;
        }
        if (dto.tipo != null) {
            documento.setTipo(dto.tipo);
        }
        if (dto.dataEmissao != null) {
            documento.setDataEmissao(dto.dataEmissao);
        }
        if (dto.numero != null) {
            documento.setNumero(dto.numero);
        }

        documento = repositorio.save(documento);
        DocumentoResponseDTO response = toResponse(documento);
        adicionador.adicionarLink(response, "documentos", response.id);
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private DocumentoResponseDTO toResponse(Documento documento) {
        DocumentoResponseDTO dto = new DocumentoResponseDTO();
        dto.id = documento.getId();
        dto.tipo = documento.getTipo();
        dto.dataEmissao = documento.getDataEmissao();
        dto.numero = documento.getNumero();
        return dto;
    }
}
