package com.iGame.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	public User findOne(Integer userId);
	
	public List<User> findAll();
	
	public User findByUserName(String userName);
	
	public User findByEmail(String email);
	
}
