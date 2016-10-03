package com.gestionCompte.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {
	
	private double decouvert;

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
    public CompteCourant(String code,int solde, Client client1, Employe employe1, double decouvert){
    	super(code,solde,client1,employe1);
    	this.decouvert=decouvert;
    }
	
	public CompteCourant(){}

}
