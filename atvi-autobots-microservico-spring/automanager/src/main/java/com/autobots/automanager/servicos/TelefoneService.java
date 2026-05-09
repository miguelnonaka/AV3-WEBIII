package com.autobots.automanager.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepositorio repositorio;

    @Autowired
    private ClienteRepositorio clienteRepo;

    public Telefone cadastrar(Telefone telefone){

        if (telefone.getCliente() == null || telefone.getCliente().getId() == null) {
            throw new RuntimeException("clienteId é obrigatório");
        }

        Cliente cliente = clienteRepo
                .findById(telefone.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        telefone.setCliente(cliente);

        return repositorio.save(telefone);
    }

    public List<Telefone> listar(){
        return repositorio.findAll();
    }

    public Telefone ListarByID(Long id){
        return repositorio.findById(id).orElse(null);
    }

    public Telefone atualizar(Long id, Telefone atualizacao){
        Telefone telefone = repositorio.getById(id);

        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(telefone, atualizacao);

        return repositorio.save(telefone);
    }

    public void deletar (Long id){
        repositorio.deleteById(id);
    }
}