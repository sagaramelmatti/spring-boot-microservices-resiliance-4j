package com.ledzer.master.service;

import java.util.List;

import com.ledzer.master.entity.Business;

public interface BusinessService {

	public List<Business> businessList();

	public void saveBusiness(Business business);

	public Business getBusinessDetails(Long id);

	public Business updateBusiness(Long businessId, Business business);

	public void deleteBusiness(Long businessId);

}
