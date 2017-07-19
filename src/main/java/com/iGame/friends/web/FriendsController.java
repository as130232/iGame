package com.iGame.friends.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iGame.exception.NoLoggingException;
import com.iGame.friends.domain.Friends;
import com.iGame.friends.domain.FriendsDTO;
import com.iGame.friends.service.FriendsService;
import com.iGame.user.domain.User;
import com.iGame.user.service.UserService;

@RestController
public class FriendsController {

	@Autowired
	private FriendsService friendsService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/friend/{friendId}")
	public User getFriend(@PathVariable Integer friendId){
		User friend = userService.getUser(friendId);
		return friend;
	}
	
	@RequestMapping("/friend/{userId}")
	public User getUser(@PathVariable Integer userId){
		return userService.getUser(userId);
	}
	
	
	@RequestMapping("/friends/user/{userId}")
	public List<FriendsDTO> getFriendsByUser(@PathVariable Integer userId) throws NoLoggingException{
		//User user = userService.getLoginUser();
		User user = userService.getUser(userId);
		List<Friends> friends = friendsService.getFriends(user);
		List<FriendsDTO> friendsDTOs = friends.stream().map(friend -> {
			return FriendsDTO.generate(friend);
		}).collect(Collectors.toList());
		return friendsDTOs;
	}
}
