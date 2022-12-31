package com.ledzer.invoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ledzer.invoice.service.CommonService;

@RestController
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	
	@GetMapping("/initial")
    public String getInvoiceInitial() {
		Long businessId = 1L;
        return commonService.getInvoiceInitial(businessId);
    }
}
