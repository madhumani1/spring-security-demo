/**
 * 
 */
package com.madhu.springsecurity.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.madhu.springsecurity.demo.service.UserService;

/**
 * @author 15197
 * Create Spring Security Configuration
 */
@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
	// inject our Datasource that we just configured
	@Autowired
    private UserService userService;
	
	@Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/**
		 ********************************************************** 
		 * Get rid of hard coded user authentications
		 **********************************************************
		 */
		// add our users for in memory authentication
		/*UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("madhu").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("prinku").password("test123").roles("MANAGER","EMPLOYEE"))
			.withUser(users.username("vicky").password("test123").roles("EMPLOYEE","ADMIN"));
		*/
		// use JDBC authentication!!!
		auth.authenticationProvider(authenticationProvider());
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
		 * 
		 * .logout().permitAll();
		 * 
		 * .exceptionHandling().accessDeniedPage()
		 */
		http.authorizeRequests()
			//.anyRequest().authenticated()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER") 
			.antMatchers("/systems/**").hasRole("ADMIN")
			
			.and()
			
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			
			.and()
			
			.logout().permitAll()
			
			.and()
			
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	//beans
	//bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
	  
}
