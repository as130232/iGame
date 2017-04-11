package com.iGame.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public User getUser(Integer userId){
		return userRepository.findOne(userId);
	}
	
	public List<User> getAllUsers(){
		List<User> userList = new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		return userList;
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
	
	
}
