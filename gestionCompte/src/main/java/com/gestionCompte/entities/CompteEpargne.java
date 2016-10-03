package com.gestionCompte.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{
	private double taux;

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
	public CompteEpargne(){
		super();
	}

	public CompteEpargne(double taux) {
		super();
		this.taux = taux;
	}
	

}
