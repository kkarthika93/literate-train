package com.CrudProduct.controller;

import java.util.List;

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

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/allProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) throws RecordNotFoundException {
		Product entity = productService.getProductById(id);
		return new ResponseEntity<Product>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Product> createOrUpdateProduct(@RequestBody Product product) throws RecordNotFoundException {
		Product updated = productService.createOrUpdateProduct(product);
		return new ResponseEntity<Product>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteProductById(@PathVariable("id") int id) throws RecordNotFoundException {
		productService.deleteProductById(id);
		return HttpStatus.FORBIDDEN;
	}

	@PostMapping("/bulkCreate")
	public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
		List<Product> createResponse = productService.saveAllProducts(products);
		return new ResponseEntity<List<Product>>(createResponse, new HttpHeaders(), HttpStatus.OK);
	}
}
