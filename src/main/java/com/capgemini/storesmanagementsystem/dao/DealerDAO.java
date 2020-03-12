package com.capgemini.storesmanagementsystem.dao;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.*;


public interface DealerDAO {
	public OrderDetails placeOrder(UserInfoBean dealer);
	public boolean setSellingPrice(DealerProductInfoBean dealer);
	public DealerProductInfoBean getProduct(int id);
	public OrderDetails getPaymentDeatils(int orderId);
	public boolean checkNameAvailability(String name) ;
	public boolean setDeliveredDate(OrderDetails order);
	public List<DealerProductInfoBean> getAllProducts(int userId);
	public boolean setMinimumQuantity(DealerProductInfoBean dealer);
}
