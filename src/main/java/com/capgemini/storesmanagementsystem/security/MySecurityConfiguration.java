package com.capgemini.storesmanagementsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capgemini.storesmanagementsystem.config.RestAuthenticationEntryPoint;
import com.capgemini.storesmanagementsystem.filters.CustomUserPasswordAuthFilter;
import com.capgemini.storesmanagementsystem.handlers.MyLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private MyLogoutSuccessHandler myLogoutSuccessHandler;
	
	@Autowired
	public AuthenticationFailureHandler myAuthFailureHandler;
	
	@Bean
	public UsernamePasswordAuthenticationFilter getUsernamePasswordAuthenticationFilter() throws Exception {
		CustomUserPasswordAuthFilter filter = new CustomUserPasswordAuthFilter();
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		filter.setAuthenticationFailureHandler(myAuthFailureHandler);
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		
		http.csrf().disable()
		
		.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/basicauth").permitAll()
		.and().httpBasic()
        .and()
		.exceptionHandling()
		.authenticationEntryPoint(restAuthenticationEntryPoint)
		.and()
		.authorizeRequests()
		.antMatchers("/addprod", "/updateCost","/getPayments", "/getAllProducts", "/updateProd","/changeStatus").hasRole("MANUFACTURER")
		.and()
		.authorizeRequests()
		.antMatchers("/updateMan", "/getAllMans", "/deleteMan","/addManufacturer").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/dealerOrder", "/setSellingPrice", "/getProds", "/getProd"
				,"/setMin", "/getMansProds","/deliveredOn","/updateDealerProduct").hasRole("DEALER")
		.and()
		.authorizeRequests()
		.antMatchers("/buyProduct", "/getDealerProds").hasRole("CUSTOMER")
		.and()
		.authorizeRequests()
		.antMatchers("/getMyOrders","/addtocart","/getItems","/remItem","/deliveredOn").hasAnyRole("CUSTOMER","DEALER")
		.and()
		.authorizeRequests()
		.antMatchers("/register").permitAll()
		.and()
		.addFilterBefore(getUsernamePasswordAuthenticationFilter(), CustomUserPasswordAuthFilter.class)
		.logout()
		.logoutSuccessHandler(myLogoutSuccessHandler);
		
	}
}
