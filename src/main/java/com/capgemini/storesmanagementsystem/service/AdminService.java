package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

public interface AdminService {
	public boolean updateManufacturerDetails(UserInfoBean manufacturer);
	public UserInfoBean getManufacturerDetails(int id);
	public List<UserInfoBean> getAllManufacturersDetails();
	public boolean removeManufacturer(int userId);
}
