package com.autobots.automanager.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.DTO.UsuarioRequestDTO;
import com.autobots.automanager.DTO.UsuarioResponseDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.modelo.AdicionadorLinkUsuario;

@Service
public class UsuarioService {

    @Autowired
    private RepositorioUsuario repositorio;

    @Autowired
    private RepositorioEmpresa repositorioEmpresa;

    @Autowired
    private AdicionadorLinkUsuario adicionador;

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome);
        usuario.setNomeSocial(dto.nomeSocial);

        if (dto.perfis != null) {
            usuario.setPerfis(dto.perfis);
        }
        if (dto.telefones != null) {
            usuario.setTelefones(dto.telefones);
        }
        if (dto.endereco != null) {
            usuario.setEndereco(dto.endereco);
        }
        if (dto.documentos != null) {
            usuario.setDocumentos(dto.documentos);
        }
        if (dto.emails != null) {
            usuario.setEmails(dto.emails);
        }
        if (dto.credenciais != null) {
            usuario.setCredenciais(dto.credenciais);
        }

        usuario = repositorio.save(usuario);

        UsuarioResponseDTO response = toResponse(usuario);
        adicionador.adicionarLink(response);
        return response;
    }

    public List<UsuarioResponseDTO> listar() {
        List<UsuarioResponseDTO> lista = repositorio.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

        adicionador.adicionarLink(lista);
        return lista;
    }

    public Usuario buscarEntidadePorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = repositorio.findById(id).orElse(null);
        if (usuario == null) {
            return null;
        }

        UsuarioResponseDTO response = toResponse(usuario);
        adicionador.adicionarLink(response);
        return response;
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = repositorio.findById(id).orElse(null);
        if (usuario == null) return null;

        if (dto.nome != null) {
            usuario.setNome(dto.nome);
        }
        if (dto.nomeSocial != null) {
            usuario.setNomeSocial(dto.nomeSocial);
        }
        if (dto.perfis != null) {
            usuario.setPerfis(dto.perfis);
        }
        if (dto.telefones != null) {
            usuario.setTelefones(dto.telefones);
        }
        if (dto.endereco != null) {
            usuario.setEndereco(dto.endereco);
        }
        if (dto.documentos != null) {
            usuario.setDocumentos(dto.documentos);
        }
        if (dto.emails != null) {
            usuario.setEmails(dto.emails);
        }
        if (dto.credenciais != null) {
            usuario.setCredenciais(dto.credenciais);
        }
        if (dto.empresaId != null) {
            Empresa empresa = repositorioEmpresa.findById(dto.empresaId).orElse(null);
            usuario.setEmpresa(empresa);
        }

        usuario = repositorio.save(usuario);

        UsuarioResponseDTO response = toResponse(usuario);
        adicionador.adicionarLink(response);
        return response;
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    private UsuarioResponseDTO toResponse(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.id = usuario.getId();
        dto.nome = usuario.getNome();
        dto.nomeSocial = usuario.getNomeSocial();
        dto.perfis = usuario.getPerfis();
        dto.telefones = usuario.getTelefones();
        dto.endereco = usuario.getEndereco();
        dto.documentos = usuario.getDocumentos();
        dto.emails = usuario.getEmails();
        dto.credenciais = usuario.getCredenciais();
        if (usuario.getEmpresa() != null) {
            dto.empresaId = usuario.getEmpresa().getId();
        }

        return dto;
    }
}