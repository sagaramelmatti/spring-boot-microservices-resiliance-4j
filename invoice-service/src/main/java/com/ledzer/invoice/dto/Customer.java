package com.ledzer.invoice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

	private Long id;
	private String name;
	private String gender;
	private String address;
	private String shipAdd;
	private String city;
	private int stateId;
	private String pin;
	private String contactNo;
	private String email;
	private String custType;
	private Double totalOutBal;
	private Double totalAmtPaid;
	private String gstno;

}
