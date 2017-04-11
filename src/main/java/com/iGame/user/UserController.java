package com.iGame.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/{userId}")
	public User getUser(@PathVariable Integer userId){
		return userService.getUser(userId);
	}
	
	@RequestMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
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
	
	
}
