package com.ledzer.master.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledzer.master.entity.Tax;
import com.ledzer.master.repository.TaxRepository;
import com.ledzer.master.service.TaxService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class TaxServiceImpl implements TaxService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
    private TaxRepository taxRepository;
	
	public List<Tax> taxList() {
		return taxRepository.findAll();
	}
	
	public void saveTax(Tax tax) {
		 taxRepository.save(tax);
	}
	
    public Tax getTaxDetails(Long id) {
    	return taxRepository.findById(id).get();
	}
     
	public Tax updateTax(Long taxId, Tax tax) {
		return taxRepository.save(tax);
	}

	public void deleteTax(Long taxId) {
		
	}
}
