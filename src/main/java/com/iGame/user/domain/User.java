package com.iGame.user.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iGame.productOrder.domain.ProductOrder;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String userName;
	private String email;
	@JsonIgnore
	private String password;
	private Integer money;

	//hibernate有lazy的問題，需要的時候再另外取值
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	@JsonBackReference(value="user-productOrder")
//	private List<ProductOrder> productOrder;
	

	
	public User(){	
	}
	
	public User(Integer userId, String userName, String password, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public User(Integer userId) {
		super();
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	

//	public List<ProductOrder> getProductOrder() {
//		return productOrder;
//	}
//
//	public void setProductOrder(List<ProductOrder> productOrder) {
//		this.productOrder = productOrder;
//	}

	
	
}
