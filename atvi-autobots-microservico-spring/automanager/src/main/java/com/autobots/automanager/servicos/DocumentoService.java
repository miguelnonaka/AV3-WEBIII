package com.autobots.automanager.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepositorio repositorio;

    @Autowired
    private ClienteRepositorio clienteRepo;

    public Documento cadastrar(Documento documento){

        if (documento.getCliente() == null || documento.getCliente().getId() == null) {
            throw new RuntimeException("clienteId é obrigatório");
        }

        Cliente cliente = clienteRepo
                .findById(documento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        documento.setCliente(cliente);

        return repositorio.save(documento);
    }

    public List<Documento> listar(){
        return repositorio.findAll();
    }

    public Documento ListarByID(Long id){
        return repositorio.findById(id).orElse(null);
    }

    public Documento atualizar(Long id, Documento atualizacao){
        Documento documento = repositorio.getById(id);

        DocumentoAtualizador atualizador = new DocumentoAtualizador();
        atualizador.atualizar(documento, atualizacao);

        return repositorio.save(documento);
    }

    public void deletar (Long id){
        repositorio.deleteById(id);
    }
}