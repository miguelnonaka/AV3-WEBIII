package com.autobots.automanager.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.autobots.automanager.enumeracoes.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = { "proprietario", "vendas" })
@Entity
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoVeiculo tipo;

	@Column(nullable = false)
	private String modelo;

	@Column(nullable = false, unique = true)
	private String placa;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Usuario proprietario;

	@OneToMany(mappedBy = "veiculo", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Venda> vendas = new HashSet<>();
}