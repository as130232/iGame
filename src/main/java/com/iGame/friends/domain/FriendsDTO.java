package com.iGame.friends.domain;

import com.iGame.user.domain.User;

public class FriendsDTO {
	
	public static FriendsDTO generate(Friends friends){
		FriendsDTO friendsDTO = new FriendsDTO();
		User friend = friends.getFriend();
		friendsDTO.setUserName(friend.getUserName());
		return friendsDTO;
	}
	
	private String userName;
	
	FriendsDTO(){
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
