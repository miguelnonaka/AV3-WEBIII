package com.autobots.automanager.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column
	private String nomeSocial;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<PerfilUsuario> perfis = new HashSet<>();

	@OneToMany(
		orphanRemoval = true,
		cascade = CascadeType.ALL,
		fetch = FetchType.EAGER
	)
	private Set<Telefone> telefones = new HashSet<>();

	@OneToOne(
		cascade = CascadeType.ALL,
		orphanRemoval = true,
		mappedBy = "cliente"
	)
	@ToString.Exclude
	private Endereco endereco;

	@OneToMany(
		orphanRemoval = true,
		cascade = CascadeType.ALL,
		fetch = FetchType.EAGER
	)
	private Set<Documento> documentos = new HashSet<>();

	@OneToMany(
		orphanRemoval = true,
		cascade = CascadeType.ALL,
		fetch = FetchType.EAGER
	)
	private Set<Email> emails = new HashSet<>();

	@OneToMany(
		orphanRemoval = true,
		cascade = CascadeType.ALL,
		fetch = FetchType.EAGER
	)
	private Set<Credencial> credenciais = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@ToString.Exclude
	private Empresa empresa;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Venda> vendas = new HashSet<>();

	@OneToMany(mappedBy = "proprietario", fetch = FetchType.EAGER)
	private Set<Veiculo> veiculos = new HashSet<>();
}