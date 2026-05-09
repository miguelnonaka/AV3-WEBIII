package com.autobots.automanager.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nomeSocial;
    private Date dataNascimento;
    private Date dataCadastro = new Date();
 
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Telefone> telefones = new ArrayList<>();


	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Documento> documentos = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Endereco endereco;


    public void addTelefone(Telefone telefone) {
        if (telefone == null) return;
        telefone.setCliente(this);
        this.telefones.add(telefone);
    }

    public void addDocumento(Documento documento) {
        if (documento == null) return;
        documento.setCliente(this);
        this.documentos.add(documento);
    }

    public void setEndereco(Endereco endereco) {
        if (endereco != null) {
            endereco.setCliente(this);
        }
        this.endereco = endereco;
    }
}