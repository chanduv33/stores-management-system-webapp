package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.CartInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

public interface UserService {
	public boolean register(UserInfoBean admin);
	//public UserInfoBean login(UserInfoBean admin);
	public boolean update(UserInfoBean bean);
	public List<OrderDetails> getOrders(int userId);
	public boolean setDeliveredDate(int orderId, String date);
	public boolean addToCart(UserInfoBean bean);
	public List<CartInfoBean> getCartItems(int userId);
	public boolean removeCartItem(int itemId);
	public boolean changeStatus(OrderDetails order);
}
