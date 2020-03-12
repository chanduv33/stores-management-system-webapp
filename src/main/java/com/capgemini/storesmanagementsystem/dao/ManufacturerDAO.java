package com.capgemini.storesmanagementsystem.dao;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.*;
public interface ManufacturerDAO {
	public boolean setCostPrice(ProductInfoBean bean);
	public OrderDetails getPaymentDetails(OrderDetails order);
	public boolean addProduct(UserInfoBean manufacturer);
	public List<ProductInfoBean> getAllProducts(int userId);
	public boolean updateProduct(ProductInfoBean bean);
}
