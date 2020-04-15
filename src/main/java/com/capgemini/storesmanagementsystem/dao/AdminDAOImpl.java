package com.capgemini.storesmanagementsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.capgemini.storesmanagementsystem.dto.UserInfoBean;
import com.capgemini.storesmanagementsystem.exceptions.EmailAlreadyExistsException;
import com.capgemini.storesmanagementsystem.exceptions.MobileNumberAlreadyExistsException;

import lombok.extern.java.Log;

@Log
@Repository
public class AdminDAOImpl implements AdminDAO{
	@PersistenceUnit
	private EntityManagerFactory fact ;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public boolean updateManufacturerDetails(UserInfoBean manufacturer) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			UserInfoBean bean = mgr.find(UserInfoBean.class, manufacturer.getUserId());
			bean.setName(manufacturer.getName());
			mgr.persist(bean);
			tx.commit();
			return true;
		} catch (Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				log.info(ele.toString());
				return false;
			}
		}
		return false;
	}

	

	@Override
	public List<UserInfoBean> getAllManufacturersDetails() {
		EntityManager mgr = fact.createEntityManager();
		try {
				String jpql = "select m from UserInfoBean m where m.role='ROLE_MANUFACTURER'";
				TypedQuery<UserInfoBean> query = mgr.createQuery(jpql, UserInfoBean.class);
				List<UserInfoBean> beans = query.getResultList();
				if (beans!=null) {
					return beans;
				} else {
					return null;
				}
		} catch (Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				log.info(ele.toString());
				return null;
			}
		}
		return null;
	}

	@Override
	public boolean removeManufacturer(int userId) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			UserInfoBean bean = mgr.find(UserInfoBean.class, userId);
			mgr.remove(bean);
			tx.commit();
			return true;
		} catch (Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				log.info(ele.toString());
				return false;
			}
		}
		return false;
	}



	@Override
	public boolean addManufacturer(UserInfoBean user) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		tx.begin();
		String jpql = "select u from UserInfoBean u where u.role='ROLE_MANUFACTURER'";
		TypedQuery<UserInfoBean> query = mgr.createQuery(jpql, UserInfoBean.class);
		List<UserInfoBean> list = query.getResultList();
		if (!list.isEmpty()) {
			for (UserInfoBean userInfoBean : list) {
				if (user.getUsername().equalsIgnoreCase(userInfoBean.getUsername())) {
					throw new EmailAlreadyExistsException();
				}
				if (user.getMobileNumber() == userInfoBean.getMobileNumber()) {
					throw new MobileNumberAlreadyExistsException();
				}
			}
		}
		user.setPassword(encoder.encode(user.getPassword()));
		mgr.persist(user);
		tx.commit();
		return true;
	}
}
