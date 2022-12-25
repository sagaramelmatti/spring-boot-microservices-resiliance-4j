package com.ledzer.master.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "mst_business_details")
public class Business {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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