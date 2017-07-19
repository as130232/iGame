package com.iGame.product.domain;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> , JpaSpecificationExecutor<Product>{
	
	public Product findByProductName(String productName);;
	
}
