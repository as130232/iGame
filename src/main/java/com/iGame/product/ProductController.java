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
	
	@RequestMapping("/product/{id}")
	public Product getMember(@PathVariable String id){
		return productService.getProduct(id);
	}
	
	@RequestMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@RequestMapping(value = "/product", method=RequestMethod.POST)
	public void addProduct(@RequestBody Product product){
		productService.addProduct(product);
	}
	
	@RequestMapping(value = "/product/{id}", method=RequestMethod.PUT)
	public void updateProduct(@RequestBody Product product, @PathVariable String id){
		productService.updateProduct(id, product);
	}
	
	@RequestMapping(value = "/product/{id}", method=RequestMethod.DELETE)
	public void deleteProduct(@PathVariable String id){
		productService.deleteProduct(id);
	}
}
