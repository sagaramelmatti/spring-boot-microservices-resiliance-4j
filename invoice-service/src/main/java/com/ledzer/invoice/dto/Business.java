package com.ledzer.invoice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Business {

	private Long id;
	private String businessName;
	private String ownerName;
	private String invoiceInitials;
	private String address;
	private String city;
	private Integer stateId;
	private String phoneNo;
	private String email;
	private String gstin;
}
