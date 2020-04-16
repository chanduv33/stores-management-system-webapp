package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.storesmanagementsystem.dao.ManufacturerDAO;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

@Service
public class ManufacturerServiceImplementation implements ManufacturerService {

	@Autowired
	private ManufacturerDAO dao;

	@Override
	public boolean setCostPrice(ProductInfoBean bean) {
		return dao.setCostPrice(bean);
	}


	@Override
	public boolean addProduct(UserInfoBean manufacturer) {
		return dao.addProduct(manufacturer);
	}

	@Override
	public List<ProductInfoBean> getAllProducts(int userId) {
		return dao.getAllProducts(userId);
	}

	@Override
	public boolean updateProduct(ProductInfoBean bean) {
		// TODO Auto-generated method stub
		return dao.updateProduct(bean);
	}



	@Override
	public List<OrderDetails> getPaymentDetails(int userId) {
		return dao.getPaymentDetails(userId);
	}


	@Override
	public boolean removeProduct(int productId) {
		return dao.removeProduct(productId);
	}
	
}
