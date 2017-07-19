package com.iGame.chart.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.iGame.user.domain.User;

@Service
public class ParticipantService {

	private Map<String, User> activeSessions = new ConcurrentHashMap<>();
	public void add(String sessionId, User event) {
		activeSessions.put(sessionId, event);
	}

	public User getParticipant(String sessionId) {
		return activeSessions.get(sessionId);
	}

	public void removeParticipant(String sessionId) {
		activeSessions.remove(sessionId);
	}

	public Map<String, User> getActiveSessions() {
		return activeSessions;
	}

	public void setActiveSessions(Map<String, User> activeSessions) {
		this.activeSessions = activeSessions;
	}
}
