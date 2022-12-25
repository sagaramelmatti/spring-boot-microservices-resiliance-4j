package com.ledzer.product.dto;

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
    private String categoryName;
    private String categoryId;
    private String rate;
    private String gstPer;
    
    public ProductDTO(String id, String name) {
    	this.id = id;
    	this.name = name;
	}
    
}
