package com.ledzer.invoice.command.eventhandler;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ledzer.invoice.command.dto.InvoiceDetailsCommandDTO;
import com.ledzer.invoice.command.event.InvoiceCreatedEvent;
import com.ledzer.invoice.dto.CustomerPaymentDetailDTO;
import com.ledzer.invoice.entity.CustomerPaymentDetails;
import com.ledzer.invoice.entity.Invoice;
import com.ledzer.invoice.entity.InvoiceDetails;
import com.ledzer.invoice.repository.CustomerPaymentDetailsRepository;
import com.ledzer.invoice.repository.InvoiceDetailsRepository;
import com.ledzer.invoice.repository.InvoiceRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEventHandler {

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

    @EventHandler
    public void invoiceCretedEvent(InvoiceCreatedEvent invoiceCreatedEvent){
        Invoice invoice = new Invoice();
        CustomerPaymentDetails customerPaymentDetails = null;
        
        BeanUtils.copyProperties(invoiceCreatedEvent, invoice);
        invoice = invoiceRepository.save(invoice);
        
        Set<InvoiceDetails> invoiceDetailsList = null;
		try {
			invoiceDetailsList = convertToInvoiceDetailsEntity(invoiceCreatedEvent.getInvoiceDetailsCommandDTO(), invoice);
			invoiceDetailsRepository.saveAll(invoiceDetailsList);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customerPaymentDetails = convertToCustomerPaymentDetailsEntity(invoiceCreatedEvent.getCustomerPaymentDetailDTO(), invoice);
		customerPaymentDetailsRepository.save(customerPaymentDetails);

    }
    
    private Set<InvoiceDetails> convertToInvoiceDetailsEntity(List<InvoiceDetailsCommandDTO> invoiceDetailsCommandDTO,
			Invoice invoice) throws ParseException {

		Set<InvoiceDetails> invoiceDetailsList = new HashSet<InvoiceDetails>();

		for (InvoiceDetailsCommandDTO invoiceDetailsDTO : invoiceDetailsCommandDTO) {
			InvoiceDetails invoiceDetails = new InvoiceDetails();
			invoiceDetails.setInvoice(invoice);
			invoiceDetails.setProductId(Long.parseLong(invoiceDetailsDTO.getProductId()));
			invoiceDetails.setHsnCode(invoiceDetailsDTO.getHsnCode());
			invoiceDetails.setQuantity(Integer.parseInt(invoiceDetailsDTO.getQuantity()));
			invoiceDetails.setRate(Double.parseDouble(invoiceDetailsDTO.getRate()));
			invoiceDetails.setTaxableAmount(Double.parseDouble(invoiceDetailsDTO.getTaxableAmount()));
			invoiceDetails.setCgstPer(Double.parseDouble(invoiceDetailsDTO.getCgstPer()));
			invoiceDetails.setSgstPer(Double.parseDouble(invoiceDetailsDTO.getSgstPer()));
			invoiceDetails.setIgstPer(Double.parseDouble(invoiceDetailsDTO.getIgstPer()));
			invoiceDetails.setCgstAmt(Double.parseDouble(invoiceDetailsDTO.getCgstAmt()));
			invoiceDetails.setSgstAmt(Double.parseDouble(invoiceDetailsDTO.getSgstAmt()));
			invoiceDetails.setIgstAmt(Double.parseDouble(invoiceDetailsDTO.getIgstAmt()));
			invoiceDetailsList.add(invoiceDetails);
		}

		return invoiceDetailsList;
	}

    private CustomerPaymentDetails convertToCustomerPaymentDetailsEntity(CustomerPaymentDetailDTO  customerPaymentDetailDTO, Invoice invoice) {

		CustomerPaymentDetails customerPaymentDetails = new CustomerPaymentDetails();
		customerPaymentDetails.setInvoice(invoice);
		customerPaymentDetails.setBusinessId(Long.parseLong(customerPaymentDetailDTO.getBusinessId()));
		customerPaymentDetails.setCustomerId(Long.parseLong(customerPaymentDetailDTO.getCustomerId()));
		customerPaymentDetails.setPaymentAmount(Double.parseDouble(customerPaymentDetailDTO.getPaymentAmount()));
		customerPaymentDetails.setPaymentType("I");
		customerPaymentDetails.setPaymentDate(new Date());

		return customerPaymentDetails;
	}
}
