package com.iGame.user.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * JpaSpecificationExecutor<User> 用於複合查詢
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> , JpaSpecificationExecutor<User>{
	
	
	public List<User> findByUserName(String userName);
	
	public User findByEmail(String email);
	
	public User findByUserNameAndEmail(String userName, String email);
	
}
