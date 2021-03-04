package com.CrudProduct.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.CrudProduct.exception.RecordNotFoundException;
import com.CrudProduct.model.Product;
import com.CrudProduct.repository.ProductRepository;
import com.CrudProduct.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Transactional
	public Product getProductById(int id) throws RecordNotFoundException {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return product.get();
		} else {
			throw new RecordNotFoundException("No product record exist for given id");
		}
	}

	@Override
	@Transactional
	public List<Product> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		if (productList.size() > 0) {
			return productList;
		} else {
			return new ArrayList<Product>();
		}

	}

	@Transactional
	public Product createOrUpdateProduct(Product entity) throws RecordNotFoundException {
		Optional<Product> product = productRepository.findById(entity.getProductId());

		if (product.isPresent()) {
			Product newEntity = product.get();
			newEntity.setProductId(entity.getProductId());
			newEntity.setProductName(entity.getProductName());
			newEntity.setProductPrice(entity.getProductPrice());

			newEntity = productRepository.save(newEntity);

			return newEntity;
		} else {
			entity = productRepository.save(entity);

			return entity;
		}
	}

	@Override
	@Transactional
	public List<Product> saveAllProducts(List<Product> products) {
		List<Product> saveResponse = productRepository.saveAll(products);
		return saveResponse;
	}

	@Transactional
	public void deleteProductById(int id) throws RecordNotFoundException {
		Optional<Product> product = productRepository.findById(id);

		if (product.isPresent()) {
			productRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No product record exist for given id");
		}
	}
}
