package com.iGame.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.iGame.dao.ConditionSpecification;
import com.iGame.product.domain.Product;
import com.iGame.product.domain.ProductRepository;
import com.iGame.user.domain.User;
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ConditionSpecification conditionSpecification;
	
	
	public Product getProduct(Integer productId){
		return productRepository.findOne(productId);
	}
	
	public List<Product> getAllProducts(){
		return (List<Product>) productRepository.findAll();
	}
	
	public void addProduct(Product product){
		productRepository.save(product);
	}
	
	public void updateProduct(Integer productId, Product product){
		productRepository.save(product);
	}
	
	public void deleteProduct(Integer productId){
		productRepository.delete(productId);
	}

	public List<Product> findByProductCondition(Product product) {
		Specification<Product> specification = conditionSpecification.generate(product);
		List<Product> result = productRepository.findAll(specification);
		return result;
	}
	
	
}
