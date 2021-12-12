/**
 * 
 */
package com.madhu.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 15197
 * showMyLoginPage should match with DemoSecurityConfig.configure(HttpSecurity).
 */
@Controller
public class LoginController {
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage()	{
		//return "plain-login";	// goes to /web-inf/view/plain-login.jsp
		return "fancy-login";	// goes to /web-inf/view/fancy-login.jsp
	}
}
