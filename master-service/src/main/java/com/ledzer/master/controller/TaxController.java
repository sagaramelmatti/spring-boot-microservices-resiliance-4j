package com.ledzer.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ledzer.master.entity.Tax;
import com.ledzer.master.service.TaxService;

@CrossOrigin
@RestController
@RequestMapping("/taxes")
public class TaxController {
	
	@Autowired
	private TaxService taxService;
	
	
	@GetMapping("/")
    public List<Tax> taxList() {
        return taxService.taxList();
    }
	
	@GetMapping("/{id}")
    public Tax getTaxDetails(@PathVariable Long id){
        Tax tax = taxService.getTaxDetails(id);
        return tax;
    }
	
	@PostMapping
    public void saveTax(@RequestBody Tax tax) {
        taxService.saveTax(tax);
    }
	
	@PutMapping("/{taxId}")
    public Tax updateTax(@PathVariable Long taxId, @RequestBody Tax tax){
        return taxService.updateTax(taxId,tax);
    }
	
	
	@DeleteMapping("/{taxId}")
    public void deleteTax(@PathVariable Long taxId){
        taxService.deleteTax(taxId);
    }
	
}
