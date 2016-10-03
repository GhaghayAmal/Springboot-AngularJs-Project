package com.gestionCompte.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionCompte.entities.Employe;

public interface IEmployeRepository extends JpaRepository<Employe, Long>{

}
