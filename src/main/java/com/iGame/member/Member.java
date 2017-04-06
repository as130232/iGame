package com.iGame.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.iGame.product.Product;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer memberId;
	private String name;
	private String password;
	private String email;
	
	@OneToMany
	private Product product;
	
	public Member(){	
	}
	
	public Member(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Member [id=" + memberId + ", name=" + name + ", password=" + password + ", email=" + email + "]";
	}

	public Integer getId() {
		return memberId;
	}

	public void setId(Integer id) {
		this.memberId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
