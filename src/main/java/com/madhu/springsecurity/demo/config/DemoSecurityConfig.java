/**
 * 
 */
package com.madhu.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

/**
 * @author 15197
 * Create Spring Security Configuration
 */
@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// add our users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("madhu").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("priyanka").password("test123").roles("MANAGER"))
			.withUser(users.username("vicky").password("test123").roles("ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/**
		 * authorizeRequests - Restrict access based on HttpServletRequest
		 * .anyRequest().authenticated() - Any requests to the app must be authenticated
		 * .formLogin() - customer login page showMyLoginPage. We need to create a controller for this request mapping.
		 * .loginProcessingUrl("/authenticateTheUser") - post the data for processing (check user id and password). 
		 * 												 No controller required, thanks to Spring.
		 * .permitAll -allow everyone to see login page.   
		 */
		http.authorizeRequests()
			.anyRequest().authenticated()
			
			.and()
			
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll();
	}
	
	
}
