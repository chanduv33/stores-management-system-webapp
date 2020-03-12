package com.capgemini.storesmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.storesmanagementsystem.dto.ResponseClass;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;
import com.capgemini.storesmanagementsystem.service.UserService;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping(path = "register",produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass register(@RequestBody UserInfoBean user) {
		ResponseClass resp = new ResponseClass();
		if(service.register(user)) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Registered Successfully");
			resp.setUser(user);
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Registration Unsuccessfull");
			return resp;
		}
	}
	
	
	@PutMapping(path = "update",produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass update(@RequestBody UserInfoBean user) {
		ResponseClass resp = new ResponseClass();
		if(service.update(user)) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Updation Successfull");
			resp.setUser(user);
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Updation failed");
			return resp;
		}
	}
	
	
	@PostMapping(path = "login",produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass login(@RequestBody UserInfoBean user) {
		ResponseClass resp = new ResponseClass();
		UserInfoBean bean = service.login(user);
		if(bean!=null) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Login Successfull");
			resp.setUser(bean);
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Login UnSuccessfull");
			return resp;
		}
	}
}
