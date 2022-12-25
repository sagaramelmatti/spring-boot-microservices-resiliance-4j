package com.ledzer.customer.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "mst_customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private Long id;
	
	@NotBlank(message = "Name must not be empty")
	@Column(length = 255, nullable = false)
	private String name;
	
	@Column(length = 1)
	private String gender;
	
	@Column(length = 100)
	private String address;
	
	@Column(name = "ship_add", length = 1000)
	private String shipAdd;
	
	@NotBlank(message = "City must not be empty")
	@Column(length = 30)
	private String city;
	
	@Column(name = "state_id", precision = 10 ,nullable = false)
	private int stateId;
	
	@Column(length = 6)
	private String pin;
	
	@NotBlank(message = "Contact No must not be empty")
	@Column(name = "contact_no", length = 10, nullable = false)
	private String contactNo;
	
	@NotBlank(message = "Email must not be empty")
	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(length = 20)
	private String gstno;
	
	@Column(name = "cust_type", length = 1)
	private String custType;
	
	@NotBlank(message = "Total Out Balance is mandatory")
	@Column(name = "total_out_bal", nullable = false, precision = 10, scale = 2)
	private Double totalOutBal;
	
	@Column(name = "total_amt_paid", precision = 10, scale = 2)
	private Double totalAmtPaid;

}