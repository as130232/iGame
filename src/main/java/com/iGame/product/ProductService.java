package com.iGame.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	public Product getProduct(String id){
		return productRepository.findOne(id);
	}
	
	public List<Product> getAllProducts(){
		List<Product> productList = new ArrayList<>();
		productRepository.findAll().forEach(productList::add);
		return productList;
	}
	
	public void addProduct(Product product){
		productRepository.save(product);
	}
	
	public void updateProduct(String id, Product product){
		productRepository.save(product);
	}
	
	public void deleteProduct(String id){
		productRepository.delete(id);
	}
	
	
}
