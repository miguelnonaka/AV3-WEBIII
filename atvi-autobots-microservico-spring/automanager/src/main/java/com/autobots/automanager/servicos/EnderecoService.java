package com.autobots.automanager.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepositorio repositorio;

    public Endereco cadastrar(Endereco endereco){
        return repositorio.save(endereco);
    } 

    public List<Endereco> listar(){
        return repositorio.findAll();
    }

    public Endereco ListarByID(Long id){
        return repositorio.findById(id).orElse(null);
    }

    public Endereco atualizar(Long id, Endereco atualizacao){
        Endereco endereco = repositorio.findById(id).orElse(null);
        if (endereco == null) return null;

        EnderecoAtualizador atualizador = new EnderecoAtualizador();
        atualizador.atualizar(endereco, atualizacao);

        return repositorio.save(endereco);
    }

    public void deletar(Long id){
        repositorio.deleteById(id);
    }
}