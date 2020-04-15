package com.capgemini.storesmanagementsystem.dao;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

public interface AdminDAO {
	public boolean addManufacturer(UserInfoBean manufacturer);
	public boolean updateManufacturerDetails(UserInfoBean manufacturer);
	public List<UserInfoBean> getAllManufacturersDetails();
	public boolean removeManufacturer(int userId);
}
