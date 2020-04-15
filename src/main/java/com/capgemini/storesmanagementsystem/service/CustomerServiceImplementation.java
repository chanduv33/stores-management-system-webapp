package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.storesmanagementsystem.dao.CustomerDAO;
import com.capgemini.storesmanagementsystem.dto.DealerProductInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

@Service
public class CustomerServiceImplementation implements CustomerService {
	
	@Autowired
	private CustomerDAO dao;

	

	@Override
	public OrderDetails buyProduct(UserInfoBean dealer) {
		return dao.buyProduct(dealer);
	}

	@Override
	public OrderDetails getOrderDetails(int id) {
		return dao.getOrderDetails(id);
	}

	@Override
	public boolean checkEmailAvailability(String email) {
		return dao.checkEmailAvailability(email);
	}


	@Override
	public void autoBuy() {
		dao.autoBuy();
	}

	@Override
	public List<DealerProductInfoBean> getProds() {
		return dao.getProds();
	}
}
