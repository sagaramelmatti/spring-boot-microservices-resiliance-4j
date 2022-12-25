package com.ledzer.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.ledzer.product.common.exceptions.ResourceNotFoundException;
import com.ledzer.product.dto.ProductDTO;
import com.ledzer.product.entity.Product;
import com.ledzer.product.service.ProductService;


@CrossOrigin
//@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;



    private String cache = null;

    private static final String MASTER_SERVICE = "master_service";

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> findAllProducts() throws ResourceNotFoundException {
        return new ResponseEntity<List<ProductDTO>>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping
    public Product create(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{productId}")
    public Product updateProductById(@PathVariable Long productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
