package com.iGame.productOrder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductOrderService {

	@Autowired
	private ProductOrderRepository productOrderRepository;
	
	
	public ProductOrder getProductOrder(Integer productOrderId){
		return productOrderRepository.findOne(productOrderId);
	}
	
	public ProductOrder getProductOrderByUsePrice(Integer usePrice){
		return productOrderRepository.findByUsePrice(usePrice);
	}
	
	public List<ProductOrder> getAllProductOrders(){
		List<ProductOrder> productOrderList = new ArrayList<>();
		productOrderRepository.findAll().forEach(productOrderList::add);
		return productOrderList;
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
	
	
}
