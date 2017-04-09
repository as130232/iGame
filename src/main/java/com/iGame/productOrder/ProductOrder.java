package com.iGame.productOrder;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.iGame.member.Member;
import com.iGame.product.Product;

@Entity
public class ProductOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productOrderId;
	
	@Transient
	private Integer memberId;
	@Transient
	private Integer productId;
	
	private Integer usePrice;

	private Date purchaseDate;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	@JsonBackReference(value="member-productOrder")
	private Member member;
	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonBackReference(value="product-productOrder")
	private Product product;
	
	
	public ProductOrder(){	
	}

	public ProductOrder(Integer productOrderId, Integer usePrice, Integer memberId, Integer productId,
			Date purchaseDate) {
		super();
		this.productOrderId = productOrderId;
		this.usePrice = usePrice;
		this.purchaseDate = purchaseDate;
		
		this.member = new Member(memberId, "", "", "");
		this.product = new Product(productId, "", null, null, "");
	}

	public Integer getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	public Integer getMemberId() {
		return this.member.getMemberId();
	}

	public void setMemberId(Integer memberId) {
		this.member.setMemberId(memberId);
	}

	public Integer getProductId() {
		return this.product.getProductId();
	}

	public void setProductId(Integer productId) {
		this.product.setProductId(productId);
	}
	
	
	
	
	
}
