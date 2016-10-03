package com.gestionCompte.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING,length=1, name="typeOP")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="type")
@JsonSubTypes({
	@Type(name="V",value=Versement.class),
	@Type(name="R", value=Retrait.class)
})
public  class Operation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numeroOperation;
	private Date dateOperation;
	private double montant;
	@ManyToOne
	@JoinColumn(name="code_compte")
	private Compte compte;
	@ManyToOne
	@JoinColumn(name="code_EMP")
	private Employe employe;
	@JsonIgnore
	public Employe getEmploye() {
		return employe;
	}
	@JsonSetter
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public Long getNumeroOperation() {
		return numeroOperation;
	}
	public void setNumeroOperation(Long numeroOperation) {
		this.numeroOperation = numeroOperation;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public Operation(Long numeroOperation, Date dateOperation, double montant, Compte compte) {
		super();
		this.numeroOperation = numeroOperation;
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}
	public Operation(){}
	
}
