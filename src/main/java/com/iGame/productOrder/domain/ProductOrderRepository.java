package com.iGame.productOrder.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iGame.user.domain.User;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Integer> , JpaSpecificationExecutor<ProductOrder>{
	
	public ProductOrder findByUsePrice(Integer usePrice);
	public List<ProductOrder> findByUser(User user);
}
