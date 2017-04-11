//package com.iGame.member;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.iGame.productOrder.ProductOrder;
//
//@Entity
//public class Member {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer memberId;
//	private String memberName;
//	private String email;
//	private String password;
//	
//	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//	@JsonManagedReference(value="member-productOrder")
//	private List<ProductOrder> productOrder;
//	
//	
//	public Member(){	
//	}
//	
//	public Member(Integer memberId, String name, String password, String email) {
//		super();
//		this.memberId = memberId;
//		this.memberName = name;
//		this.password = password;
//		this.email = email;
//	}
//
//	public Member(Integer memberId) {
//		super();
//		this.memberId = memberId;
//	}
//
//	public Integer getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(Integer memberId) {
//		this.memberId = memberId;
//	}
//
//	public String getMemberName() {
//		return memberName;
//	}
//
//	public void setMemberName(String memberName) {
//		this.memberName = memberName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public List<ProductOrder> getProductOrder() {
//		return productOrder;
//	}
//
//	public void setProductOrder(List<ProductOrder> productOrder) {
//		this.productOrder = productOrder;
//	}
//
//	
//	
//}
