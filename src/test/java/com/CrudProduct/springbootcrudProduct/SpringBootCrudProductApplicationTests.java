package com.CrudProduct.springbootcrudProduct;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.CrudProduct.exception.RecordNotFoundException;
import com.CrudProduct.model.Product;
import com.CrudProduct.repository.ProductRepository;
import com.CrudProduct.serviceImpl.ProductServiceImpl;

public class SpringBootCrudProductApplicationTests {

	@InjectMocks
	ProductServiceImpl service;

	@Mock
	ProductRepository repository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllProductsTest() {
		List<Product> list = new ArrayList<>();
		Product productOne = new Product(1, "bag", 1000);
		Product productTwo = new Product(2, "candy", 500);
		Product productThree = new Product(3, "mobile", 50000);

		list.add(productOne);
		list.add(productTwo);
		list.add(productThree);

		when(repository.findAll()).thenReturn(list);

		List<Product> productList = service.getAllProducts();
		assertEquals(3, productList.size());
		verify(repository, times(1)).findAll();
	}

	@Test
	public void getProductByIdTest() throws RecordNotFoundException {
		when(repository.findById(1)).thenReturn(Optional.of(new Product(1, "dress", 12345)));
		Product product = service.getProductById(1);
		assertEquals(1, product.getProductId());
		assertEquals("dress", product.getProductName());
		assertEquals(12345, product.getProductPrice());
	}

	@Test
	public void getProductByIdExcTest() throws RecordNotFoundException {
		try {
			when(repository.findById(1)).thenReturn(Optional.of(new Product(2, "dress", 12345)));
			service.getProductById(1);
		} catch (Exception e) {
			assertEquals("No product record exist for given id", e.getMessage());
		}
	}

	@Test
	public void createAllProductsTest() {
		List<Product> list = new ArrayList<>();
		Product productOne = new Product(1, "bag", 1000);
		Product productTwo = new Product(2, "candy", 500);
		Product productThree = new Product(3, "mobile", 50000);

		list.add(productOne);
		list.add(productTwo);
		list.add(productThree);

		service.saveAllProducts(list);

		verify(repository, times(1)).saveAll(list);
	}

	@Test
	public void deleteProductByIdTest() throws RecordNotFoundException {
		when(repository.findById(1)).thenReturn(Optional.of(new Product(1, "dress", 12345)));
		service.deleteProductById(1);
		verify(repository, times(1)).deleteById(1);
	}

	@Test
	public void deleteProductByIdExcTest() throws RecordNotFoundException {
		try {
			when(repository.findById(1)).thenReturn(Optional.of(new Product(2, "dress", 12345)));
			service.deleteProductById(1);
		} catch (Exception e) {
			assertEquals("No product record exist for given id", e.getMessage());
		}
	}

	@Test
	public void createOrUpdateProductTest() throws RecordNotFoundException {
		when(repository.findById(1)).thenReturn(Optional.of(new Product(1, "dress", 12345)));
		Product product = service.getProductById(1);
		service.createOrUpdateProduct(product);
		verify(repository, times(1)).save(product);
	}
}
