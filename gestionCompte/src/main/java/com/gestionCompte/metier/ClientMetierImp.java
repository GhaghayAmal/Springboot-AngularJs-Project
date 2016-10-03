package com.gestionCompte.metier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestionCompte.dao.IClientRepository;
import com.gestionCompte.dao.ICompteRepository;
import com.gestionCompte.dao.IEmployeRepository;
import com.gestionCompte.dao.IOperationRepository;
import com.gestionCompte.entities.Client;
import com.gestionCompte.entities.Compte;
import com.gestionCompte.entities.Employe;
import com.gestionCompte.entities.Operation;
import com.gestionCompte.entities.Retrait;
import com.gestionCompte.entities.Versement;


@Service
public class ClientMetierImp implements ClientMetier{
	@Autowired
	private IClientRepository clientRepository;
	@Autowired
	private IEmployeRepository employeRepository;
	@Autowired
    private ICompteRepository compteRepository;
	@Autowired
	private IOperationRepository operationRepository;
	@Override
	public Client saveClient(Client client) {
		
		return clientRepository.save(client);
	}

	@Override
	public List<Client> listClient() {
		
		return clientRepository.findAll();
	}

	@Override
	public Employe saveEmploye(Employe employe) {
		
		return employeRepository.save(employe);
	}

	@Override
	public List<Employe> listEmploye() {
		
		return employeRepository.findAll();
	}

	@Override
	public Compte saveCompte(Compte compte) {
		
		return compteRepository.save(compte);
	}

	@Override
	public Compte getCompte(String code) {
		Compte cp=compteRepository.findOne(code);
		if(cp==null)throw new RuntimeException("Compte Inexistant!");
		return cp;
	}

	@Override
	@Transactional
	public boolean verser(String code, double montant, Long codeEmp) {
		Compte cpt=compteRepository.findOne(code);
		Operation op=new Versement();
		op.setDateOperation(new Date());
		op.setMontant(montant);
		op.setEmploye(employeRepository.findOne(codeEmp));
		operationRepository.save(op);
		cpt.setSolde(cpt.getSolde()+montant);
		return true;
	}

	@Override
	@Transactional
	public boolean retirer(String code, double montant, Long codeEmp) {
		Compte cpt=compteRepository.findOne(code);
		if(cpt.getSolde()<montant)throw new RuntimeException("Solde insuffisant");
		Operation op=new Retrait();
		op.setDateOperation(new Date());
		op.setMontant(montant);
		op.setEmploye(employeRepository.findOne(codeEmp));
		operationRepository.save(op);
		cpt.setSolde(cpt.getSolde()-montant);
		return true;
	}

	@Override
	@Transactional
	public boolean virement(String cpt1, String cpt2, double montant, Long codeEmp) {
		retirer(cpt1, montant, codeEmp);
		verser(cpt2, montant, codeEmp);
		return true;
	}

	@Override
	public PageOperation getOperations(String codeCompte, int page, int size) {
		Page<Operation> operations=operationRepository.getOperations(codeCompte, new PageRequest(page, size));;
		PageOperation pageOp= new PageOperation();
		pageOp.setOperations(operations.getContent());
		pageOp.setNombreOperation(operations.getNumberOfElements());
		pageOp.setTotalPages(operations.getTotalPages());
		pageOp.setTotalOperation((int)operations.getTotalElements());
		return pageOp;
	}

	

}
