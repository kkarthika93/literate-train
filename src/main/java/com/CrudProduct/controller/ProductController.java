package com.CrudProduct.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CrudProduct.exception.RecordNotFoundException;
import com.CrudProduct.model.Product;
import com.CrudProduct.service.ProductService;
import com.CrudProduct.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private ProductService productService;

	@GetMapping("/allProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		Long startTime = System.currentTimeMillis();
		logger.debug("getAllProducts started at {} ms", startTime);
		List<Product> list = productService.getAllProducts();
		logger.debug("getAllProducts ended at {} ms", (System.currentTimeMillis() - startTime));
		return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) throws RecordNotFoundException {
		Long startTime = System.currentTimeMillis();
		logger.debug("getProductById started at {} ms", startTime);
		Product entity = productService.getProductById(id);
		logger.debug("getProductById ended at {} ms", (System.currentTimeMillis() - startTime));
		return new ResponseEntity<Product>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Product> createOrUpdateProduct(@RequestBody Product product) throws RecordNotFoundException {
		Long startTime = System.currentTimeMillis();
		logger.debug("createOrUpdateProduct started at {} ms", startTime);
		Product updated = productService.createOrUpdateProduct(product);
		logger.debug("createOrUpdateProduct ended at {} ms", (System.currentTimeMillis() - startTime));
		return new ResponseEntity<Product>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteProductById(@PathVariable("id") int id) throws RecordNotFoundException {
		Long startTime = System.currentTimeMillis();
		logger.debug("deleteProductById started at {} ms", startTime);
		productService.deleteProductById(id);
		logger.debug("deleteProductById ended at {} ms", (System.currentTimeMillis() - startTime));
		return HttpStatus.FORBIDDEN;
	}

	@PostMapping("/bulkCreate")
	public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
		Long startTime = System.currentTimeMillis();
		logger.debug("createProducts started at {} ms", startTime);
		List<Product> createResponse = productService.saveAllProducts(products);
		logger.debug("createProducts ended at {} ms", (System.currentTimeMillis() - startTime));
		return new ResponseEntity<List<Product>>(createResponse, new HttpHeaders(), HttpStatus.OK);
	}
}
