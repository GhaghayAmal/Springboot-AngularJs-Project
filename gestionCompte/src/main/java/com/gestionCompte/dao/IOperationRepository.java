package com.gestionCompte.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestionCompte.entities.Operation;

public interface IOperationRepository extends JpaRepository<Operation, Long>{
    @Query("select o from Operation o where o.compte.code=:x")
	public Page<Operation> getOperations(@Param("x") String code,Pageable page);
}
