package com.iGame.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iGame.exception.NoLoggingException;
import com.iGame.product.domain.Product;
import com.iGame.product.domain.ProductRepository;
import com.iGame.user.domain.User;
import com.iGame.user.domain.UserRepository;
import com.iGame.user.service.UserService;

@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/user/{userId}")
	public User getUser(@PathVariable Integer userId){
		return userService.getUser(userId);
	}
	
	@RequestMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/userCondition", method=RequestMethod.POST)
	public List<User> userCondition(@RequestBody User user){
		List<User> result =  userService.findByUserCondition(user);
		return result;
	}
	
	@RequestMapping(value = "/user", method=RequestMethod.POST)
	public void addUser(@RequestBody User user){
		userService.addUser(user);
	}
	
	@RequestMapping(value = "/user/{userId}", method=RequestMethod.PUT)
	public void updateUser(@RequestBody User user, @PathVariable Integer userId){
		userService.updateUser(userId, user);
	}
	
	@RequestMapping(value = "/user/{userid}", method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
	}
	
	@RequestMapping(value = "/loginUser", method=RequestMethod.GET)
	public User getLoginUser() throws NoLoggingException{
		return userService.getLoginUser();
	}
	
	@RequestMapping(value = "/debit/{productId}", method=RequestMethod.GET)
	public User debit(@PathVariable Integer productId) throws NoLoggingException{
		//取得現在登入的會員資訊
		User user = userService.getLoginUser();
		Product product = productRepository.findOne(productId);
		user = userService.debit(user, product);
		return user;
	}
	
	
}
