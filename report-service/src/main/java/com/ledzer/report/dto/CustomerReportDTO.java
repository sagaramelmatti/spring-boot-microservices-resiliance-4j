package com.ledzer.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerReportDTO {

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
	
}
