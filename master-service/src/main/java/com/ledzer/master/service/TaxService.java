package com.ledzer.master.service;

import java.util.List;

import com.ledzer.master.entity.Tax;

public interface TaxService {

	public List<Tax> taxList();

	public void saveTax(Tax tax);

	public Tax getTaxDetails(Long id);

	public Tax updateTax(Long taxId, Tax tax);

	public void deleteTax(Long taxId);

}
