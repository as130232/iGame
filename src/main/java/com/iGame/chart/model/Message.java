package com.iGame.chart.model;

public class Message {

	private String userName;
	private String message;
	
	public Message(){
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ChatMessage [user=" + userName + ", message=" + message + "]";
	}
}
