package com.iGame.url;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UrlController {
	
	@RequestMapping("/")
		String index(){
			return "index";
		}
}
