package com.iGame.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productName;
	private String type;
	private Integer price;
	private String imgSrc;
	
	public Product(){
	}

	public Product(Integer productId, String productName, String type, Integer price, String imgSrc) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.type = type;
		this.price = price;
		this.imgSrc = imgSrc;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

}
