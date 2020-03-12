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

	/*
	 * @Override public boolean checkNameAvailability(String name) { return
	 * dao.checkNameAvailability(name); }
	 */

	@Override
	public boolean setCostPrice(ProductInfoBean bean) {
		return dao.setCostPrice(bean);
	}

	@Override
	public OrderDetails getPaymentDetails(OrderDetails order) {
		return dao.getPaymentDetails(order);
	}

	/*
	 * @Override public ManufacturerInfoBean login(ManufacturerInfoBean bean) {
	 * return dao.login(bean); }
	 */

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

	
}
