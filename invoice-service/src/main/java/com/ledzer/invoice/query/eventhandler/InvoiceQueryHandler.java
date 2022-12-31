package com.ledzer.invoice.query.eventhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ledzer.invoice.dto.Business;
import com.ledzer.invoice.dto.Customer;
import com.ledzer.invoice.dto.CustomerPaymentDetailDTO;
import com.ledzer.invoice.dto.InvoiceDTO;
import com.ledzer.invoice.dto.InvoiceDetailsDTO;
import com.ledzer.invoice.entity.Invoice;
import com.ledzer.invoice.entity.InvoiceDetails;
import com.ledzer.invoice.query.query.GetInvoiceQuery;
import com.ledzer.invoice.repository.InvoiceRepository;
import com.ledzer.invoice.service.InvoiceService;

@Component
public class InvoiceQueryHandler {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceService invoiceService;

	@Value("${masterAPI}")
	private String masterAPI;

	@Value("${customerAPI}")
	private String customerAPI;

	@Autowired
	private ModelMapper modelMapper;

	@QueryHandler
	public List<InvoiceDTO> getInvoiceList(GetInvoiceQuery invoiceQuery) {

		List<InvoiceDTO> employeeResponses = new ArrayList<>();
		Optional<List<Invoice>> invoiceList = Optional.ofNullable(invoiceRepository.findAll());

		if (invoiceList.isPresent()) {
			employeeResponses = invoiceList.get().stream().map(this::convertToDto).collect(Collectors.toList());
		}

		return employeeResponses;
	}

	/*
	@QueryHandler
	public InvoiceDTO getInvoice(FindInvoiceQuery query) {

		InvoiceDTO invoiceDTO = new InvoiceDTO();

		Optional<Invoice> invoice = Optional.ofNullable(invoiceRepository.findByUid(query.getUid()));

		if (invoice.isPresent()) {
			invoiceDTO = this.convertToDto(invoice.get());
		}

		return invoiceDTO;
	}
	*/

	private InvoiceDTO convertToDto(Invoice invoice) {

		InvoiceDTO invoiceDTO = modelMapper.map(invoice, InvoiceDTO.class);

		/* Set Business Details value */
		Business business = invoiceService.getBusiness(invoice.getBusinessId());
		invoiceDTO = setBusinessDetails(business, invoiceDTO);

		/* Set Customer Details value */
		Customer customer = invoiceService.getCustomer(invoice.getCustomerId());
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
	 * private Customer getCustomer(Long customerId) { Customer customer = null; try
	 * { customer = restTemplate.getForObject(customerAPI + "/" + customerId,
	 * Customer.class); } catch (Exception e) { System.out.println(e); }
	 * 
	 * return customer; }
	 * 
	 * 
	 * private Business getBusiness(Long businessId) {
	 * 
	 * String URI_BUSINESS_ID = new
	 * StringBuilder(masterAPI).append("/business/").toString(); Business business =
	 * restTemplate.getForObject(URI_BUSINESS_ID + businessId, Business.class);
	 * return business; }
	 */

	private InvoiceDetailsDTO convertToInvoiceDetailDto(InvoiceDetails invoiceDetails) {

		InvoiceDetailsDTO invoiceDetailsDTO = modelMapper.map(invoiceDetails, InvoiceDetailsDTO.class);

		return invoiceDetailsDTO;
	}

}