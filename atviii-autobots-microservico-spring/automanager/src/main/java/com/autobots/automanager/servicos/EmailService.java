package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.EmailRequestDTO;
import com.autobots.automanager.DTO.EmailResponseDTO;
import com.autobots.automanager.entidades.Email;
import com.autobots.automanager.modelo.AdicionadorLinkGenerico;
import com.autobots.automanager.repositorios.RepositorioEmail;

@Service
public class EmailService {

    @Autowired
    private RepositorioEmail repositorio;

    @Autowired
    private AdicionadorLinkGenerico adicionador;

    public EmailResponseDTO cadastrar(EmailRequestDTO dto) {
        Email email = new Email();
        email.setEndereco(dto.endereco);

        email = repositorio.save(email);

        EmailResponseDTO response = toResponse(email);
        adicionador.adicionarLink(response, "emails", response.id);
        return response;
    }

    public List<EmailResponseDTO> listar() {
        List<EmailResponseDTO> lista = repositorio.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        lista.forEach(email -> adicionador.adicionarLink(email, "emails", email.id));
        return lista;
    }

    public EmailResponseDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::toResponse)
                .map(email -> {
                    adicionador.adicionarLink(email, "emails", email.id);
                    return email;
                })
                .orElse(null);
    }

    public EmailResponseDTO atualizar(Long id, EmailRequestDTO dto) {
        Email email = repositorio.findById(id).orElse(null);
        if (email == null) {
            return null;
        }
        if (dto.endereco != null) {
            email.setEndereco(dto.endereco);
        }

        email = repositorio.save(email);
        EmailResponseDTO response = toResponse(email);
        adicionador.adicionarLink(response, "emails", response.id);
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private EmailResponseDTO toResponse(Email email) {
        EmailResponseDTO dto = new EmailResponseDTO();
        dto.id = email.getId();
        dto.endereco = email.getEndereco();
        return dto;
    }
}
