package com.ledzer.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "mst_products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private Long id;

	@NotBlank(message = "Name must not be empty")
	@Column(length = 100)
	private String name;

	@Column(name = "hsn_code", length = 20)
	private String hsnCode;

	@NotNull(message = "Rate must not be empty")
	@Column(nullable = false, precision = 10, scale = 2)
	private Double rate;
	
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "tax_id")
	private Long taxId;

	@Column(length = 100)
	private String description;

}
