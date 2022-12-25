package com.ledzer.master.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledzer.master.entity.Business;
import com.ledzer.master.repository.BusinessRepository;
import com.ledzer.master.service.BusinessService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class BusinessServiceImpl implements BusinessService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
    private BusinessRepository businessRepository;
	
	public List<Business> businessList() {
		return businessRepository.findAll();
	}
	
	public void saveBusiness(Business business) {
		 businessRepository.save(business);
		
	}
	
    public Business getBusinessDetails(Long id) {
    	return businessRepository.findById(id).get();
	}
     
	public Business updateBusiness(Long businessId, Business business) {
		return businessRepository.save(business);
	}

	public void deleteBusiness(Long businessId) {
		
	}
}
