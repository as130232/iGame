package com.iGame.user;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.iGame.product.domain.Product;
import com.iGame.product.service.ProductService;
import com.iGame.user.domain.User;
import com.iGame.user.domain.UserRepository;
import com.iGame.user.service.UserService;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest
@Transactional	//所有測試執行完會rockback
public class UserTest {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	
	@Test  
	public void testGetUser(){  
		Integer userId = 1;
		//User user = userRepository.findOne(userId); 
		User user = userService.getUser(userId);
		Assert.assertEquals("succes", "Peter", user.getUserName());  
		//assertEquals("Peter", user.getUserName());
	}  
	
	@Test
    public void testDebit() {
		Integer userId = 1;
		User user = userService.getUser(userId);
		Integer userMoney = user.getMoney();
		Integer productId = 1;
		Product product = productService.getProduct(productId);
		Integer productPrice= product.getPrice();
		Integer expected = userMoney - productPrice;
		User result = userService.debit(user, product);
		assertEquals(expected, result.getMoney(), 0);
	}
	
	
	@Test(expected=NegativeArraySizeException.class)
    public void testDebitWithoutEnoughMoney() {
		Integer userId = 3;
		User user = userService.getUser(userId);
		Integer userMoney = user.getMoney();
		Integer productId = 1;
		Product product = productService.getProduct(productId);
		Integer productPrice= product.getPrice();
		Integer expected = userMoney - productPrice;
		userService.debit(user, product);
	}
}
