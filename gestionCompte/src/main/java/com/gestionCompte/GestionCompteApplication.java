package com.gestionCompte;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.gestionCompte.dao.IClientRepository;
import com.gestionCompte.dao.ICompteRepository;
import com.gestionCompte.dao.IEmployeRepository;
import com.gestionCompte.entities.Client;
import com.gestionCompte.entities.Compte;
import com.gestionCompte.entities.CompteCourant;
import com.gestionCompte.entities.Employe;
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class GestionCompteApplication {
	@Bean
	CommandLineRunner init(IEmployeRepository employeRepository,IClientRepository clientRepository,ICompteRepository compteRepository ){
		return (evt)->{
			Employe employe1=employeRepository.save(new Employe("Empolye1"));
		
			Client client1=clientRepository.save(new Client("client1"));
			Client client2=clientRepository.save(new Client("client2"));
			Compte cpt=new  CompteCourant("CC1",50,client1,employe1,200);
			Compte cpt2=new CompteCourant("CC2",6000,client2,employe1,300);
			compteRepository.save(cpt);
			compteRepository.save(cpt2);
			
			
			
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(GestionCompteApplication.class, args);
		
	}
}
