package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.storesmanagementsystem.dao.UserDAO;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserDAO dao;

	@Override
	public boolean register(UserInfoBean user) {
		return dao.register(user);
	}

	@Override
	public UserInfoBean login(UserInfoBean user) {
		return dao.login(user);
	}

	@Override
	public boolean update(UserInfoBean bean) {
		return dao.update(bean);
	}
}
