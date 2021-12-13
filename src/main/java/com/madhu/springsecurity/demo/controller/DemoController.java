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
public class DemoController {

	@GetMapping("/")
	public String showHome()	{
		return "home";	// goes to /web-inf/view/home.jsp
	}
	
	// add request for /leaders
	@GetMapping("/leaders")
	public String showLeaders()	{
		return "leaders";	// goes to /web-inf/view/leaders.jsp
	}
	
	// add request for /systems
	@GetMapping("/systems")
	public String showSystems()	{
		return "systems";	// goes to /web-inf/view/systems.jsp
	}
}
