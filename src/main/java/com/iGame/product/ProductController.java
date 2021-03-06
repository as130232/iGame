package com.iGame.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/product/{productId}")
	public Product getProduct(@PathVariable Integer productId){
		return productService.getProduct(productId);
	}
	
	@RequestMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@RequestMapping(value = "/product", method=RequestMethod.POST)
	public void addProduct(@RequestBody Product product){
		productService.addProduct(product);
	}
	
	@RequestMapping(value = "/product/{productId}", method=RequestMethod.PUT)
	public void updateProduct(@RequestBody Product product, @PathVariable Integer productId){
		productService.updateProduct(productId, product);
	}
	
	@RequestMapping(value = "/product/{productId}", method=RequestMethod.DELETE)
	public void deleteProduct(@PathVariable Integer productId){
		productService.deleteProduct(productId);
	}
}
