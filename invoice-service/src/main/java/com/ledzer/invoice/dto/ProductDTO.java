package com.ledzer.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
	
	private String id;
	private String name;
	private String hsnCode;
	private String rate;
	private String gstPer;
	
	public ProductDTO(String id , String name)
	{
		this.id = id;
		this.name = name;
	}
	
	
}
