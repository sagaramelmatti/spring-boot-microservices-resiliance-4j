package com.ledzer.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerPaymentDetailDTO {

	private String id;
	private String paymentDate;
	private String paymentAmount;
	private String paymentType;
	private String invoiceId;
	private String customerId;
	private String businessId;
}
