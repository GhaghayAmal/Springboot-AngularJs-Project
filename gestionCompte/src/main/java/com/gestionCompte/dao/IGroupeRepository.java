package com.gestionCompte.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionCompte.entities.Groupe;

public interface IGroupeRepository extends JpaRepository<Groupe, Long>{

}
