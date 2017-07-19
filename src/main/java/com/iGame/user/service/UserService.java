package com.iGame.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iGame.dao.ConditionSpecification;
import com.iGame.exception.NoLoggingException;
import com.iGame.product.domain.Product;
import com.iGame.product.domain.ProductRepository;
import com.iGame.productOrder.domain.ProductOrder;
import com.iGame.productOrder.domain.ProductOrderRepository;
import com.iGame.user.domain.User;
import com.iGame.user.domain.UserRepository;
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductOrderRepository productOrderRepository;
	@Autowired
	private ConditionSpecification conditionSpecification;
	
	
	
	public User getUser(Integer userId){
		return userRepository.findOne(userId);
	}
	
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	public void addUser(User user){
		userRepository.save(user);
	}
	
	public void updateUser(Integer userId, User user){
		userRepository.save(user);
	}
	
	public void deleteUser(Integer userId){
		userRepository.delete(userId);
	}
	/*
	 * 取得現在登入的會員資訊
	 */
	public User getLoginUser() throws NoLoggingException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//若未登入導至登入頁面
		String email = authentication.getName();
		if("anonymousUser".equals(email)){
			throw new NoLoggingException("No Logging. Please login.");
		}
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	
	/*
	 * 交易部分，扣款、新增交易明細
	 */
	@Transactional
	public User debit(User user, Product product){
		if(user == null || product == null){
			throw new NullPointerException("user or product can not be null");
		}
		//先扣款
		Integer balance = user.getMoney() - product.getPrice();
		if(balance < 0){
			throw new NegativeArraySizeException("Insufficient balance");
		}
		user.setMoney(balance);
		userRepository.save(user);
		//新增訂單明細
		ProductOrder productOrder = new ProductOrder();
		productOrder.setUsePrice(product.getPrice());
		productOrder.setUser(user);
		productOrder.setProduct(product);
		productOrder.setPurchaseDate(new Date());
		productOrderRepository.save(productOrder);
		User result = userRepository.findOne(user.getUserId());
		
		return result;
	}
	

	public List<User> findByUserCondition(User user){
		//Specification<User> specification = generateSpecification(user);
		Specification<User> specification = conditionSpecification.generate(user);
		List<User> result = userRepository.findAll(specification);
		return result;
	}

	private Specification<User> generateSpecification(User user) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if (user.getUserId() != null) {
					predicates.add(cb.equal(root.get("userId"), user.getUserId()));
				}
				if (user.getUserName() != null) {
					predicates.add(cb.equal(root.get("userName"), user.getUserName()));
				}
				if (user.getEmail() != null) {
					predicates.add(cb.equal(root.get("email"), user.getEmail()));
				}
				
				return cb.and(predicates.toArray(new Predicate[0]));
			}
	    };
		  
		
	}
	
}
