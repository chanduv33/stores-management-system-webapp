package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

public interface UserService {
	public boolean register(UserInfoBean admin);
	public UserInfoBean login(UserInfoBean admin);
	public boolean update(UserInfoBean bean);
}
