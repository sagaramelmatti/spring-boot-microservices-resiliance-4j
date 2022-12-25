package com.ledzer.product.service;

import java.util.List;

import com.ledzer.product.dto.ProductDTO;
import com.ledzer.product.entity.Product;

public interface ProductService {

	public ProductDTO findById(Long id);

	public List<ProductDTO> findAll();

	public Product createProduct(Product product);

	public Product updateProduct(Long customerId, Product customer);

	public void deleteProduct(Long customerId);

	public List<ProductDTO> findAllProducts();

	public ProductDTO getProductsDetails(Long id);

}