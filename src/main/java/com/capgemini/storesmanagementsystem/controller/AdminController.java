package com.capgemini.storesmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.storesmanagementsystem.dto.ResponseClass;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;
import com.capgemini.storesmanagementsystem.exceptions.EmailAlreadyExistsException;
import com.capgemini.storesmanagementsystem.exceptions.MobileNumberAlreadyExistsException;
import com.capgemini.storesmanagementsystem.service.AdminService;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*",exposedHeaders="Access-Control-Allow-Origin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@PostMapping(path = "/updateMan",produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass updateManufacturer(@RequestBody UserInfoBean bean) {
		ResponseClass resp = new ResponseClass();
		if(service.updateManufacturerDetails(bean)) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Updation Successfull");
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Updation Product Failed");
			return resp;
		}
	}
	
	
	@GetMapping(path = "/getAllMans",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass getAllManufacturer() {
		ResponseClass resp = new ResponseClass();
		List<UserInfoBean> bean =service.getAllManufacturersDetails();
		if(bean!=null) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Manufacturer Found");
			resp.setUsers(bean);
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Manufacturer Not Found");
			return resp;
		}
	}
	
	@GetMapping(path="/deleteMan",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass removeManufacturer(@RequestParam("userId")int userId) {
		ResponseClass resp = new ResponseClass();
		if(service.removeManufacturer(userId)) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Manufacturer Found");
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Manufacturer Not Found");
			return resp;
		}
	}
	
	@PostMapping(path = "/addManufacturer",produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass addManufacturer(@RequestBody UserInfoBean user) {
		ResponseClass resp = new ResponseClass();
		
		try {
		boolean result = service.addManufacturer(user);
		if(result) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Added Successfully");
			resp.setUser(user);
			return resp;
		}
		} catch (EmailAlreadyExistsException exp) {
			resp.setStatusCode(407);
			resp.setMessage("Failed");
			resp.setDescription(exp.getMessage());
			return resp;
		} catch (MobileNumberAlreadyExistsException exp) {
			resp.setStatusCode(408);
			resp.setMessage("Failed");
			resp.setDescription(exp.getMessage());
			return resp;
		} catch (Exception exp) {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Addition of manufacturer Unsuccessfull");
			return resp;
		}
		return resp;
	}
	
}
