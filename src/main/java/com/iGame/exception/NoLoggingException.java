package com.iGame.exception;

public class NoLoggingException extends Exception{
	public NoLoggingException(String str){
		//呼叫父類別的建構子
		super(str);   
    }
}
