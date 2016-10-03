package com.gestionCompte.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionCompte.entities.Compte;

public interface ICompteRepository extends JpaRepository<Compte, String>{

}
