package com.autobots.automanager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entitades.CredencialCodigoBarra;

public interface RepositorioCredencialBarra extends JpaRepository<CredencialCodigoBarra, Long> {
	//public Empresa findByRazaoSocial(String nome);
}