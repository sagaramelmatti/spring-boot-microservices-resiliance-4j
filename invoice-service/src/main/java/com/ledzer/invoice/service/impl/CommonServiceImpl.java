package com.ledzer.invoice.service.impl;

import java.lang.invoke.MethodHandles;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledzer.invoice.dto.Business;
import com.ledzer.invoice.repository.CustomerPaymentDetailsRepository;
import com.ledzer.invoice.service.CommonService;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private CustomerPaymentDetailsRepository customerPaymentDetailsRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${masterAPI}")
	private String masterAPIApi;

	@Override
	public String getInvoiceInitial(Long businessId) {

		String URI_CATEGORY_ID = new StringBuilder(masterAPIApi).append("/business/").toString();
		Business business = restTemplate.getForObject(URI_CATEGORY_ID + businessId, Business.class);
		return business.getInvoiceInitials();
	}
}