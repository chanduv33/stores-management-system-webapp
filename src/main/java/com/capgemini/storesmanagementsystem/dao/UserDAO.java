package com.capgemini.storesmanagementsystem.dao;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.*;

public interface UserDAO {
	
	public boolean register(UserInfoBean admin);
	public UserInfoBean login(UserInfoBean admin);
	public boolean update(UserInfoBean bean);
	
}
