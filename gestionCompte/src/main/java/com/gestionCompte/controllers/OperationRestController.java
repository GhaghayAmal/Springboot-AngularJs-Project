package com.gestionCompte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestionCompte.metier.ClientMetier;
import com.gestionCompte.metier.PageOperation;

public class OperationRestController {
	@Autowired
	private ClientMetier clientMetier;
    @RequestMapping(value="/versement", method=RequestMethod.PUT)
	public boolean verser(@RequestParam String code,@RequestParam double montant,@RequestParam Long codeEmp) {
		return clientMetier.verser(code, montant, codeEmp);
	}
    @RequestMapping(value="/retrait", method=RequestMethod.PUT)
	public boolean retirer(@RequestParam String code, @RequestParam double montant,@RequestParam Long codeEmp) {
		return clientMetier.retirer(code, montant, codeEmp);
	}
    @RequestMapping(value="/virement", method=RequestMethod.PUT)
	public boolean virement(@RequestParam String cpt1,@RequestParam String cpt2,@RequestParam double montant,@RequestParam Long codeEmp) {
		return clientMetier.virement(cpt1, cpt2, montant, codeEmp);
	}
    @RequestMapping(value="/operations", method=RequestMethod.GET)
	public PageOperation getOperations(@RequestParam String codeCompte,@RequestParam int page,@RequestParam int size) {
		return clientMetier.getOperations(codeCompte, page, size);
	}
	

}
