package com.ledzer.invoice.query.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledzer.invoice.dto.Business;
import com.ledzer.invoice.dto.Customer;
import com.ledzer.invoice.dto.CustomerPaymentDetailDTO;
import com.ledzer.invoice.dto.InvoiceDTO;
import com.ledzer.invoice.dto.InvoiceDetailsDTO;
import com.ledzer.invoice.entity.Invoice;
import com.ledzer.invoice.entity.InvoiceDetails;
import com.ledzer.invoice.query.query.FindCustomerInvoiceQuery;
import com.ledzer.invoice.query.query.FindInvoiceQuery;
import com.ledzer.invoice.query.query.GetInvoiceQuery;
import com.ledzer.invoice.service.InvoiceService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceQueryService {

	private final QueryGateway queryGateway;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private InvoiceService invoiceService;

	private final EventStore eventStore;

	public List<InvoiceDTO> findAllInvoices() {

		List<InvoiceDTO> invoiceDTOList = null;
		try {

			List<Invoice> invoiceList = queryGateway
					.query(new GetInvoiceQuery(), ResponseTypes.multipleInstancesOf(Invoice.class)).join();

			invoiceDTOList = invoiceList.stream().map(this::convertToDto).collect(Collectors.toList());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceDTOList;
	}

	public InvoiceDTO findById(String id) {

		InvoiceDTO invoiceDTO = null;

		CompletableFuture<Invoice> invoice = this.queryGateway.query(new FindInvoiceQuery(id),
				ResponseTypes.instanceOf(Invoice.class));

		try {
			invoiceDTO = convertToDto(invoice.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invoiceDTO;
	}
	
	public List<InvoiceDTO> findByCustomerId(String customerId) {

		List<Invoice> invoices = queryGateway.query(new FindCustomerInvoiceQuery(customerId), ResponseTypes.multipleInstancesOf(Invoice.class))
				.join();

		return invoices.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<Object> listEventsForAccount(String uid) {
		return this.eventStore.readEvents(uid).asStream().map(Message::getPayload).collect(Collectors.toList());
	}

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