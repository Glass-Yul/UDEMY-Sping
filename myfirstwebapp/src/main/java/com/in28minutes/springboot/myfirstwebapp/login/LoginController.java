package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("login")
	public String gotoLoginPage(@RequestParam String name ,Model model) {
		model.addAttribute("name", name);
		System.out.println("Request param is "+name);
		return "login";
	}
	
	
	
}
