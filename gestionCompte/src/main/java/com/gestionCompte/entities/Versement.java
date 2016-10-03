package com.gestionCompte.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("versement")
public class Versement extends Operation{
	public Versement(){
		super();
	}

}
