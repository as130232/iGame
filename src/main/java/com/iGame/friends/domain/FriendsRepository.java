package com.iGame.friends.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iGame.user.domain.User;

@Repository
public interface FriendsRepository  extends CrudRepository<Friends, Integer> , JpaSpecificationExecutor<Friends>{
	
	public List<Friends> findByUser(User user);
}
