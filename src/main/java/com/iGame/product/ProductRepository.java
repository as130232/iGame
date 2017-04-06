package com.iGame.product;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String>{
	
	public Product findOne(String id);
	
	public List<Product> findAll();
	
	public Product findByName(String name);;
	
}
