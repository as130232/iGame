package com.iGame.friends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iGame.friends.domain.Friends;
import com.iGame.friends.domain.FriendsPK;
import com.iGame.friends.domain.FriendsRepository;
import com.iGame.user.domain.User;

@Service
public class FriendsService {

	@Autowired
	private FriendsRepository friendsRepository;
	
//	public Friends getFriend(User user, User friend){
//		Friends result = friendsRepository.findByUserAndFriend(user, friend);
//		return result;
//	}
	
	public List<Friends> getFriends(User user){
		List<Friends> result = friendsRepository.findByUser(user);
		return result;
	}
	
}
