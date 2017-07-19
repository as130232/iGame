package com.iGame.chart.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iGame.chart.service.ParticipantService;
import com.iGame.exception.NoLoggingException;
import com.iGame.user.domain.User;
import com.iGame.user.service.UserService;

@RestController
public class ChartController {
	
	@Autowired
	private UserService userService;
	@Autowired 
	private ParticipantService participantService;
	
	@RequestMapping(value = "/chart", method = RequestMethod.GET)
	public String chartPage(HttpServletRequest request, Model model) throws NoLoggingException {
		User user = userService.getLoginUser();
		model.addAttribute("userName", user.getUserName());
		return "chart";
	}

	@SubscribeMapping("/chat.participants")
	public Collection<User> retrieveParticipants() {
		return participantService.getActiveSessions().values();
	}
}
