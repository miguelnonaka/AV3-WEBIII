package com.autobots.automanager.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = { "cliente", "funcionario", "veiculo" })
@Entity
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date cadastro;

	@Column(nullable = false, unique = true)
	private String identificacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Usuario cliente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Usuario funcionario;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Mercadoria> mercadorias = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Servico> servicos = new HashSet<>();

	@OneToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Veiculo veiculo;
}