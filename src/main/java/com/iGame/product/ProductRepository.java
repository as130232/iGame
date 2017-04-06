package com.iGame.product;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	public Product findOne(Integer productId);
	
	public List<Product> findAll();
	
	public Product findByProductName(String productName);;
	
}
