package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

public interface ManufacturerService {
	//public boolean checkNameAvailability(String name) ;
	public boolean setCostPrice(ProductInfoBean bean);
	public List<OrderDetails> getPaymentDetails(int userId);
	//public ManufacturerInfoBean login(ManufacturerInfoBean bean);
	public boolean addProduct(UserInfoBean bean);
	public List<ProductInfoBean> getAllProducts(int userId);
	public boolean updateProduct(ProductInfoBean bean);
	public boolean removeProduct(int productId);
}
