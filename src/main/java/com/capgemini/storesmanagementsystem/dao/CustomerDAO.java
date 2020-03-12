package com.capgemini.storesmanagementsystem.dao;

import com.capgemini.storesmanagementsystem.dto.*;


public interface CustomerDAO {
	public OrderDetails buyProduct(UserInfoBean dealer);
	public OrderDetails getOrderDetails(int id);
	public boolean checkEmailAvailability(String email);
	public void autoBuy();
	public boolean setDeliveredDate(OrderDetails order);
}
