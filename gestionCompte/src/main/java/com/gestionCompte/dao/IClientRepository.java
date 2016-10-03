package com.gestionCompte.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionCompte.entities.Client;

public interface IClientRepository extends JpaRepository<Client, Long>{

}
