package com.iGame.productOrder.domain;

import java.util.Date;

public class ProductOrderCondition {
	
	private Integer productOrderId;
	private Integer userId;
	private Integer productId;
	private Integer usePrice;
	private Date purchaseDate;
	
	
	public Integer getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getUsePrice() {
		return usePrice;
	}
	public void setUsePrice(Integer usePrice) {
		this.usePrice = usePrice;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	
}
