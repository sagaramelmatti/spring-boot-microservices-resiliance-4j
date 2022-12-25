package com.ledzer.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
	private String id;
	private String name;
	private String contactNo;
	private String address;
	private String city;
	private String pin;
	private String stateId;
	private String email;
	private String gstno;
	private String totalOutBal;
	private String custType;
	
	public CustomerDTO(String id, String name) {
		this.id = id;
		this.name = name;
	}

}
