package com.ledzer.product.service.impl;

import java.lang.invoke.MethodHandles;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.ledzer.product.common.exceptions.ResourceNotFoundException;
import com.ledzer.product.dto.Category;
import com.ledzer.product.dto.ProductDTO;
import com.ledzer.product.dto.Tax;
import com.ledzer.product.entity.Product;
import com.ledzer.product.repository.ProductRepository;
import com.ledzer.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${masterAPI}")
    private String masterAPIApi;

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not found with id = " + id));
        ProductDTO productDTO = convertToDto(product);
        return productDTO;
    }

    public List<ProductDTO> findAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product product) {
        /*
         * return productRepository.findById(productId) .flatMap(dbproduct -> {
         * dbproduct.setFirstName(product.getFirstName());
         * dbproduct.setLastName(product.getLastName());
         * dbproduct.setAge(product.getAge()); return productRepository.save(dbproduct);
         * });
         */
        return null;
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);

    }

    private Product convertToEntity(ProductDTO productDTO) throws ParseException {
        Product product = modelMapper.map(productDTO, Product.class);
        /*
         * Category category = new Category();
         * category.setId(Long.parseLong(productDTO.getCategoryId()));
         * product.setCategory(category);
         */
        return product;
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> new ProductDTO(Long.toString(product.getId()), product.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductsDetails(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + id));
        ProductDTO productDTO = convertToDto(product);
        return productDTO;

    }

    private ProductDTO convertToDto(Product product) {
        System.out.println("Master service Gettin called");
        // Parse the string after getting the response
        Category category = null;
        Tax tax = null;
        String URI_CATEGORY_ID = new StringBuilder(masterAPIApi).append("/categories/").toString();
        category = restTemplate.getForObject(URI_CATEGORY_ID + product.getCategoryId(), Category.class);
        String TAX_CATEGORY_ID = new StringBuilder(masterAPIApi).append("/taxes/").toString();
        tax = restTemplate.getForObject(TAX_CATEGORY_ID + product.getTaxId(), Tax.class);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setCategoryName(category.getName());
        productDTO.setGstPer(tax.getPercentage());
        return productDTO;
    }

}