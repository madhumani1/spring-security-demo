/**
 * 
 */
package com.madhu.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 15197
 * Develop Spring Controller
 */
@Controller
public class DemoConttroller {

	@GetMapping("/")
	public String showHome()	{
		return "home";	// goes to /web-inf/view/home.jsp
	}
}
