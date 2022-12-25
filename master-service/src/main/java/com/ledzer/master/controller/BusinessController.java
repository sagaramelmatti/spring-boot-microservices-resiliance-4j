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

import com.ledzer.master.entity.Business;
import com.ledzer.master.service.BusinessService;

@CrossOrigin
@RestController
@RequestMapping("/business")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	
	@GetMapping("/")
    public List<Business> businessList() {
        return businessService.businessList();
    }
	
	@GetMapping("/{id}")
    public Business getBusinessDetails(@PathVariable Long id){
        Business business = businessService.getBusinessDetails(id);
        return business;
    }
	
	@PostMapping
    public void saveBusiness(@RequestBody Business business) {
        businessService.saveBusiness(business);
    }
	
	@PutMapping("/{businessId}")
    public Business updateBusiness(@PathVariable Long businessId, @RequestBody Business business){
        return businessService.updateBusiness(businessId,business);
    }
	
	
	@DeleteMapping("/{businessId}")
    public void deleteBusiness(@PathVariable Long businessId){
        businessService.deleteBusiness(businessId);
    }
	
}
