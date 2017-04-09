package com.iGame.productOrder;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Integer>{
	
	public ProductOrder findOne(Integer productOrderId);
	
	public ProductOrder findByUsePrice(Integer usePrice);
	
	public List<ProductOrder> findAll();
	
	
}
