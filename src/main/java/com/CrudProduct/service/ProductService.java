package com.CrudProduct.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.CrudProduct.exception.RecordNotFoundException;
import com.CrudProduct.model.Product;

@Component
public interface ProductService {
	
	public Product getProductById(int id) throws RecordNotFoundException;
	public void deleteProductById(int id) throws RecordNotFoundException;
	public List<Product> saveAllProducts(List<Product> product);
	public List<Product> getAllProducts();
	public Product createOrUpdateProduct(Product product) throws RecordNotFoundException;

}
