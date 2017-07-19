package com.iGame.authentication.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class LoginAttemptService {
//	@Autowired
//	private HttpServletRequest request;
	
	private final int MAX_ATTEMPT = 2;
	private final int bolckTimeMins = 1;
	private LoadingCache<String, Integer> blockList;

	public LoginAttemptService() {
		blockList = CacheBuilder.newBuilder().expireAfterWrite(bolckTimeMins, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}
	
	/*
	 * 每當用戶登入成功便透過 LoginAttemptService，將該 ip 從 block list 中清除
	 */
	public void loginSucceeded(String key) {
		blockList.invalidate(key);
	}
	
	/*
	 * 每當用戶登入失敗就透過 LoginAttemptService，將該 ip 放入 block list 中，並記錄失敗次數。
	 */
	public void loginFailed(String key) {
		int attempts = 0;
		try {
			//取得對應ip登入失敗的次數，並累加
			attempts = blockList.get(key);
		} catch (ExecutionException e) {
			attempts = 0;
		}
		attempts++;
		blockList.put(key, attempts);
	}
	
	public boolean isBlocked(String key) {
		try {
			return blockList.get(key) >= MAX_ATTEMPT;
		} catch (ExecutionException e) {
			return false;
		}
	}
}
