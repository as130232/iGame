package com.iGame.productOrder;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iGame.member.Member;
import com.iGame.product.Product;

@RestController
@RequestMapping
public class ProductOrderController {
	@Autowired
	private ProductOrderService productService;
	
	@RequestMapping("/productOrder/{productOrderId}")
	public ProductOrder getProductOrder(@PathVariable Integer productOrderId){
		ProductOrder productOrder = productService.getProductOrder(productOrderId);
		return productOrder;
	}
	
	@RequestMapping("/productOrders")
	public List<ProductOrder> getAllProductOrders() {
		return productService.getAllProductOrders();
	}
	
	@RequestMapping(value = "/productOrder/{memberId}/{productId}", method=RequestMethod.POST)
	public void addProductOrder(@RequestBody ProductOrder productOrder,@PathVariable Integer memberId,@PathVariable Integer productId){
		productOrder.setMember(new Member(memberId));
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
