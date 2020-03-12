package com.capgemini.storesmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ResponseClass;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;
import com.capgemini.storesmanagementsystem.service.CustomerService;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*")
public class CustomerController {

	@Autowired
	private CustomerService service;
	

	@PostMapping(path = "buyProduct",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass buyProduct(@RequestBody UserInfoBean bean) {
		ResponseClass resp = new ResponseClass();
		OrderDetails order = service.buyProduct(bean);
		if(order!=null) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Order Placed Successfully");
			resp.setOrder(order);
			service.autoBuy();
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Placing Order Unsuccessfull");
			return resp;
		}
	}
	
	@PostMapping(path = "deliveredDate",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass setDeliveredDate(@RequestBody OrderDetails bean) {
		ResponseClass resp = new ResponseClass();
		if(service.setDeliveredDate(bean)) {
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
	
	@GetMapping(path = "getOrder",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass getOrder(@RequestParam("orderId")int orderId ) {
		ResponseClass resp = new ResponseClass();
		OrderDetails order = service.getOrderDetails(orderId);
		if(order!=null) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Order Found");
			resp.setOrder(order);
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Order Not Found");
			return resp;
		}
	}
	
	
}
