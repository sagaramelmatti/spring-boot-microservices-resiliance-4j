package com.ledzer.invoice.service.impl;

import java.lang.invoke.MethodHandles;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledzer.invoice.common.exceptions.ResourceNotFoundException;
import com.ledzer.invoice.dto.Business;
import com.ledzer.invoice.dto.Customer;
import com.ledzer.invoice.dto.CustomerPaymentDetailDTO;
import com.ledzer.invoice.dto.InvoiceDTO;
import com.ledzer.invoice.dto.InvoiceDetailsDTO;
import com.ledzer.invoice.entity.CustomerPaymentDetails;
import com.ledzer.invoice.entity.Invoice;
import com.ledzer.invoice.entity.InvoiceDetails;
import com.ledzer.invoice.repository.CustomerPaymentDetailsRepository;
import com.ledzer.invoice.repository.InvoiceDetailsRepository;
import com.ledzer.invoice.repository.InvoiceRepository;
import com.ledzer.invoice.service.InvoiceService;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Value("${masterAPI}")
	private String masterAPI;

	@Value("${customerAPI}")
	private String customerAPI;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepository;

	@Autowired
	private CustomerPaymentDetailsRepository customerPaymentDetailsRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ModelMapper modelMapper;

	public InvoiceDTO findById(Long id) {
		Invoice invoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Invoice with id = " + id));

		InvoiceDTO invoiceDTO = convertToDto(invoice);
		return invoiceDTO;

	}

	public List<InvoiceDTO> getInvoiceList() {
		List<Invoice> invoiceList = invoiceRepository.findAll();
		return invoiceList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public Invoice createInvoice(InvoiceDTO invoiceDTO) {
		Invoice invoice = null;
		CustomerPaymentDetails customerPaymentDetails = null;
		try {
			invoice = convertToEntity(invoiceDTO);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		invoice = invoiceRepository.save(invoice);

		Set<InvoiceDetails> invoiceDetailsList = null;
		try {
			invoiceDetailsList = convertToInvoiceDetailsEntity(invoiceDTO.getInvoiceDetailsDTOList(), invoice);
			invoiceDetailsRepository.saveAll(invoiceDetailsList);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customerPaymentDetails = convertToCustomerPaymentDetailsEntity(invoiceDTO, invoice);
		customerPaymentDetailsRepository.save(customerPaymentDetails);

		return invoice;

	}

	public Invoice updateInvoice(Long invoiceId, Invoice invoice) {
		/*
		 * return invoiceRepository.findById(invoiceId) .flatMap(dbinvoice -> {
		 * dbinvoice.setFirstName(invoice.getFirstName());
		 * dbinvoice.setLastName(invoice.getLastName());
		 * dbinvoice.setAge(invoice.getAge()); return invoiceRepository.save(dbinvoice);
		 * });
		 */
		return null;
	}

	public void deleteInvoice(Long invoiceId) {
		invoiceRepository.deleteById(invoiceId);

	}

	private InvoiceDTO setBusinessDetails(Business business, InvoiceDTO invoiceDTO) {

		invoiceDTO.setBusinessName(business.getBusinessName());
		invoiceDTO.setBusinessAddress(business.getAddress());
		invoiceDTO.setBusinessCity(business.getCity());
		invoiceDTO.setBusinessContactNo(business.getPhoneNo());
		invoiceDTO.setBusinessStateName(business.getStateId().toString());
		invoiceDTO.setBusinessStateId(business.getStateId().toString());
		invoiceDTO.setBusinessGstno(business.getGstin());

		return invoiceDTO;
	}

	private InvoiceDTO setCustomerDetails(Customer customer, InvoiceDTO invoiceDTO) {

		invoiceDTO.setCustomerId(customer.getId().toString());
		invoiceDTO.setCustomerName(customer.getName());
		invoiceDTO.setCustomerAddress(customer.getAddress());
		invoiceDTO.setCustomerCity(customer.getCity());
		invoiceDTO.setCustomerEmail(customer.getEmail());
		invoiceDTO.setCustomerContactNo(customer.getContactNo());
		invoiceDTO.setCustomerStateName(Integer.toString(customer.getStateId()));
		invoiceDTO.setCustomerStateId(Integer.toString(customer.getStateId()));
		invoiceDTO.setCustomerGstno(customer.getGstno());
		return invoiceDTO;
	}

	/*
	 * private InvoiceDTO setCustomerPaymentDetails(CustomerPaymentDetails
	 * customerPaymentDetails, InvoiceDTO invoiceDTO) {
	 * 
	 * 
	 * CustomerPaymentDetailDTO customerPaymentDetailDTO = new
	 * CustomerPaymentDetailDTO();
	 * customerPaymentDetailDTO.setPaymentDate(customerPaymentDetails.getPaymentDate
	 * ());
	 * 
	 * return invoiceDTO; }
	 */

	private Invoice convertToEntity(InvoiceDTO invoiceDTO) throws ParseException {

		Invoice invoice = new Invoice();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		// invoice.setId(Long.parseLong(invoiceDTO.getId()));
		invoice.setName(invoiceDTO.getName());
		invoice.setInvoiceNumber(Integer.parseInt(invoiceDTO.getInvoiceNumber()));
		invoice.setInvoiceDate(sdf.parse(invoiceDTO.getInvoiceDate()));
		invoice.setInvoiceYear(2022);
		invoice.setInvoiceType(invoiceDTO.getInvoiceType());
		invoice.setCustomerId(Long.parseLong(invoiceDTO.getCustomerId()));
		invoice.setBusinessId(Long.parseLong(invoiceDTO.getBusinessId()));

		invoice.setItemTotal(Double.parseDouble(invoiceDTO.getItemTotal()));
		invoice.setItemTax(Double.parseDouble(invoiceDTO.getItemTax()));
		invoice.setBillTotal(Double.parseDouble(invoiceDTO.getBillTotal()));
		invoice.setRoundValue(Double.parseDouble(invoiceDTO.getRoundValue()));
		invoice.setInvoiceTotal(Double.parseDouble(invoiceDTO.getInvoiceTotal()));
		invoice.setAmountPaid(Double.parseDouble(invoiceDTO.getAmountPaid()));
		invoice.setAmountDue(Double.parseDouble(invoiceDTO.getAmountDue()));

		return invoice;
	}

	private CustomerPaymentDetails convertToCustomerPaymentDetailsEntity(InvoiceDTO invoiceDTO, Invoice invoice) {

		CustomerPaymentDetails customerPaymentDetails = new CustomerPaymentDetails();
		customerPaymentDetails.setInvoice(invoice);
		customerPaymentDetails.setBusinessId(Long.parseLong(invoiceDTO.getBusinessId()));
		customerPaymentDetails.setCustomerId(Long.parseLong(invoiceDTO.getCustomerId()));
		customerPaymentDetails.setPaymentAmount(Double.parseDouble(invoiceDTO.getAmountPaid()));
		customerPaymentDetails.setPaymentType("I");
		customerPaymentDetails.setPaymentDate(new Date());

		return customerPaymentDetails;
	}

	private Set<InvoiceDetails> convertToInvoiceDetailsEntity(List<InvoiceDetailsDTO> invoiceDetailsDTOList,
			Invoice invoice) throws ParseException {

		Set<InvoiceDetails> invoiceDetailsList = new HashSet<InvoiceDetails>();

		for (InvoiceDetailsDTO invoiceDetailsDTO : invoiceDetailsDTOList) {
			InvoiceDetails invoiceDetails = new InvoiceDetails();
			invoiceDetails.setInvoice(invoice);
			invoiceDetails.setProductId(Long.parseLong(invoiceDetailsDTO.getProductId()));
			invoiceDetails.setHsnCode(invoiceDetailsDTO.getHsnCode());
			invoiceDetails.setQuantity(Integer.parseInt(invoiceDetailsDTO.getQuantity()));
			invoiceDetails.setRate(Double.parseDouble(invoiceDetailsDTO.getRate()));
			invoiceDetails.setCgstPer(0.00);
			invoiceDetails.setSgstPer(0.00);
			invoiceDetails.setIgstPer(0.00);

			invoiceDetails.setCgstAmt(0.00);
			invoiceDetails.setSgstAmt(0.00);
			invoiceDetails.setIgstAmt(0.00);

			invoiceDetailsList.add(invoiceDetails);

		}

		return invoiceDetailsList;
	}

	private CustomerPaymentDetailDTO convertToCustomerPaymentDetailDto(CustomerPaymentDetails customerPaymentDetails) {
		CustomerPaymentDetailDTO customerPaymentDetailDTO = modelMapper.map(customerPaymentDetails,
				CustomerPaymentDetailDTO.class);
		return customerPaymentDetailDTO;
	}

	@Override
	public Long getInvoiceNoSeq() {
		Long invoiceNo = invoiceRepository.getInvoiceNoSeq();
		if (invoiceNo == 0)
			return (long) 1;
		else
			return (long) invoiceNo + 1;
	}

	@Override
	public Long getInvoiceId() {
		Long invoiceId = invoiceRepository.getInvoiceId();
		return (long) invoiceId + 1;
	}

	

	private InvoiceDTO convertToDto(Invoice invoice) {

		InvoiceDTO invoiceDTO = modelMapper.map(invoice, InvoiceDTO.class);

		/* Set Business Details value */
		Business business = getBusiness(invoice.getBusinessId());
		invoiceDTO = setBusinessDetails(business, invoiceDTO);

		/* Set Customer Details value */
		Customer customer = getCustomer(invoice.getCustomerId());
		// invoiceDTO.setCustomerId(customer.getId().toString());
		// invoiceDTO.setCustomerName(customer.getName());

		// Customer customer = new Customer();
		invoiceDTO = setCustomerDetails(customer, invoiceDTO);

		List<InvoiceDetailsDTO> invoiceDetailsDTOList = invoice.getInvoiceDetails().stream()
				.map(this::convertToInvoiceDetailDto).collect(Collectors.toList());
		invoiceDTO.setInvoiceDetailsDTOList(invoiceDetailsDTOList);

		CustomerPaymentDetailDTO customerPaymentDetailDTO = modelMapper.map(invoice.getCustomerPaymentDetails(),
				CustomerPaymentDetailDTO.class);
		invoiceDTO.setCustomerPaymentDetailDTO(customerPaymentDetailDTO);
		// invoiceDTO = setCustomerPaymentDetails(invoice.getCustomerPaymentDetails(),
		// invoiceDTO);

		// BeanUtils.copyProperties(invoice.getCustomerPaymentDetails(),
		// invoiceDTO.getCustomerPaymentDetailDTO());

		return invoiceDTO;
	}
	
	public Customer getCustomer(Long customerId) {
		Customer customer = null;
		try {
			customer = restTemplate.getForObject(customerAPI + "/" + customerId, Customer.class);
		} catch (Exception e) {
			System.out.println(e);
		}

		return customer;
	}

	public Business getBusiness(Long businessId) {

		String URI_BUSINESS_ID = new StringBuilder(masterAPI).append("/business/").toString();
		Business business = restTemplate.getForObject(URI_BUSINESS_ID + businessId, Business.class);
		return business;
	}
	
	private InvoiceDetailsDTO convertToInvoiceDetailDto(InvoiceDetails invoiceDetails) {

		InvoiceDetailsDTO invoiceDetailsDTO = modelMapper.map(invoiceDetails, InvoiceDetailsDTO.class);

		return invoiceDetailsDTO;
	}

}