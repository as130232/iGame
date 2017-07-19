package com.iGame.productOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.iGame.dao.ConditionSpecification;
import com.iGame.productOrder.domain.ProductOrder;
import com.iGame.productOrder.domain.ProductOrderRepository;
import com.iGame.user.domain.User;
@Service
public class ProductOrderService {

	@Autowired
	private ProductOrderRepository productOrderRepository;
	@Autowired
	private ConditionSpecification conditionSpecification;
	
	public ProductOrder getProductOrder(Integer productOrderId){
		return productOrderRepository.findOne(productOrderId);
	}
	
	public List<ProductOrder> getProductOrderByUser(User user){
		return productOrderRepository.findByUser(user);
	}
	
	public ProductOrder getProductOrderByUsePrice(Integer usePrice){
		return productOrderRepository.findByUsePrice(usePrice);
	}
	
	public List<ProductOrder> getAllProductOrders(){
		return (List<ProductOrder>) productOrderRepository.findAll();
	}
	
	public void addProductOrder(ProductOrder productOrder){
		productOrderRepository.save(productOrder);
	}
	
	public void updateProductOrder(Integer productOrderId, ProductOrder product){
		productOrderRepository.save(product);
	}
	
	public void deleteProductOrder(Integer productOrderId){
		productOrderRepository.delete(productOrderId);
	}

	
	public List<ProductOrder> findByProductOrderCondition(ProductOrder productOrder) {
		Specification<ProductOrder> specification = conditionSpecification.generate(productOrder);
		List<ProductOrder> result = productOrderRepository.findAll(specification);
		return result;
	}	
}
