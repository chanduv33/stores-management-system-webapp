package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.DealerProductInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

public interface CustomerService {
	public OrderDetails buyProduct(UserInfoBean dealer);
	public OrderDetails getOrderDetails(int id);
	public boolean checkEmailAvailability(String email);
	public void autoBuy();
	public List<DealerProductInfoBean> getProds();
}
