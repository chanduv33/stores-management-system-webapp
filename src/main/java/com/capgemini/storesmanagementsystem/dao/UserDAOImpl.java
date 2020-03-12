package com.capgemini.storesmanagementsystem.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.storesmanagementsystem.dto.*;

import lombok.extern.java.Log;

@Repository
@Log
public class UserDAOImpl implements UserDAO {

	@PersistenceUnit
	private EntityManagerFactory fact ;

	@Override
	public boolean register(UserInfoBean user) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			mgr.persist(user);
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
	public UserInfoBean login(UserInfoBean admin) {
		EntityManager mgr = fact.createEntityManager();
		try {
				String jpql = "select a from UserInfoBean a where a.email=:email";
				TypedQuery<UserInfoBean> query = mgr.createQuery(jpql, UserInfoBean.class);
				query.setParameter("email", admin.getEmail());
				UserInfoBean bean = query.getSingleResult();
				if (bean.getEmail().equals(admin.getEmail()) && bean.getPassword().equals(admin.getPassword())) {
					return bean;
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
	public boolean update(UserInfoBean bean) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			UserInfoBean updateBean = mgr.find(UserInfoBean.class,bean.getUserId());
			updateBean.setPassword(bean.getPassword());
			mgr.persist(updateBean);
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

}
