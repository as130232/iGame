package com.iGame.product.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.iGame.productOrder.domain.ProductOrder;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productName;
	private Integer type;
	private Integer price;
	private String imgSrc;
	
	//hibernate有lazy的問題，需要的時候再另外取值
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	@JsonBackReference(value="product-productOrder")
//	private List<ProductOrder> productOrder;
	
	public Product(){
	}

	public Product(Integer productId, String productName, Integer type, Integer price, String imgSrc) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.type = type;
		this.price = price;
		this.imgSrc = imgSrc;
	}

	public Product(Integer productId) {
		super();
		this.productId = productId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	
//	public List<ProductOrder> getProductOrder() {
//		return productOrder;
//	}
//
//	public void setProductOrder(List<ProductOrder> productOrder) {
//		this.productOrder = productOrder;
//	}

}
