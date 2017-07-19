package com.iGame.productOrder.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iGame.exception.NoLoggingException;
import com.iGame.product.domain.Product;
import com.iGame.productOrder.domain.ProductOrder;
import com.iGame.productOrder.domain.ProductOrderCondition;
import com.iGame.productOrder.service.ProductOrderService;
import com.iGame.user.domain.User;
import com.iGame.user.service.UserService;

@RestController
@RequestMapping
public class ProductOrderController {
	
	@Autowired
	private ProductOrderService productService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/productOrder/{productOrderId}")
	public ProductOrder getProductOrder(@PathVariable Integer productOrderId){
		ProductOrder productOrder = productService.getProductOrder(productOrderId);
		return productOrder;
	}
	
	@RequestMapping("/productOrders")
	public List<ProductOrder> getAllProductOrders() {
		return productService.getAllProductOrders();
	}
	
	@RequestMapping("/productOrders/user/{userId}")
	public List<ProductOrder> getProductOrdersByUser() throws NoLoggingException {
		User user = userService.getLoginUser();
		return productService.getProductOrderByUser(user);
	}

	@RequestMapping(value = "/productOrderCondition", method=RequestMethod.POST)
	public List<ProductOrder> productOrderCondition(@RequestBody ProductOrderCondition productOrderCondition) {
		
		ProductOrder productOrder = new ProductOrder();
		
		Integer productId = productOrderCondition.getProductId();
		if(productId != null){
			Product product = new Product();
			product.setProductId(productId);
			productOrder.setProduct(product);
		}
		
		Integer userId = productOrderCondition.getUserId();
		if(userId != null){
			User user = new User();
			user.setUserId(userId);
			productOrder.setUser(user);
		}
		
		productOrder.setProductOrderId(productOrderCondition.getProductOrderId());
		productOrder.setPurchaseDate(productOrderCondition.getPurchaseDate());
		productOrder.setUsePrice(productOrderCondition.getUsePrice());
		
		List<ProductOrder> result = productService.findByProductOrderCondition(productOrder);
		return result;
	}
	
	@RequestMapping(value = "/productOrder/{userId}/{productId}", method=RequestMethod.POST)
	public void addProductOrder(@RequestBody ProductOrder productOrder, @PathVariable Integer userId, @PathVariable Integer productId){
		productOrder.setUser(new User(userId));
		productOrder.setProduct(new Product(productId));
		productOrder.setPurchaseDate(new Date());
		productService.addProductOrder(productOrder);
	}
	
	/* 訂單不開放修改 及 刪除
	@RequestMapping(value = "/productOrder/{productOrderId}", method=RequestMethod.PUT)
	public void updateProductOrder(@RequestBody ProductOrder product, @PathVariable Integer productOrderId){
		productService.updateProductOrder(productOrderId, product);
	}

	@RequestMapping(value = "/productOrder/{productOrderId}", method=RequestMethod.DELETE)
	public void deleteProductOrder(@PathVariable Integer productOrderId){
		productService.deleteProductOrder(productOrderId);
	}
	*/
}
