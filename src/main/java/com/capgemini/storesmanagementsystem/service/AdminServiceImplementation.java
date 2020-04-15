package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.storesmanagementsystem.dao.AdminDAO;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

@Service
public class AdminServiceImplementation implements AdminService{
	
	@Autowired
	private AdminDAO dao;
	

	@Override
	public boolean updateManufacturerDetails(UserInfoBean manufacturer) {
		return dao.updateManufacturerDetails(manufacturer);
	}

	@Override
	public List<UserInfoBean> getAllManufacturersDetails() {
		return dao.getAllManufacturersDetails();
	}

	@Override
	public boolean removeManufacturer(int userId) {
		return dao.removeManufacturer(userId);
	}

	@Override
	public boolean addManufacturer(UserInfoBean manufacturer) {
		return dao.addManufacturer(manufacturer);
	}
}
