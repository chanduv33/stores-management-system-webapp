package com.capgemini.storesmanagementsystem.service;

import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

public interface CustomerService {
	public OrderDetails buyProduct(UserInfoBean dealer);
	public OrderDetails getOrderDetails(int id);
	public boolean checkEmailAvailability(String email);
	public boolean setDeliveredDate(OrderDetails order);
	public void autoBuy();
}
