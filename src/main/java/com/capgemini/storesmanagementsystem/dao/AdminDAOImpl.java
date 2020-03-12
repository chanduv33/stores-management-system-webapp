package com.capgemini.storesmanagementsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.storesmanagementsystem.dto.UserInfoBean;

import lombok.extern.java.Log;

@Log
@Repository
public class AdminDAOImpl implements AdminDAO{
	@PersistenceUnit
	private EntityManagerFactory fact ;

	@Override
	public boolean updateManufacturerDetails(UserInfoBean manufacturer) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			UserInfoBean bean = mgr.find(UserInfoBean.class, manufacturer.getUserId());
			//pending
			mgr.persist(manufacturer);
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
	public UserInfoBean getManufacturerDetails(int id) {
		EntityManager mgr = fact.createEntityManager();
		try {
			UserInfoBean bean = mgr.find(UserInfoBean.class, id);
			return bean;
		} catch (Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				log.info(ele.toString());
				return null;
			}
		}
		return null;
	}

	@Override
	public List<UserInfoBean> getAllManufacturersDetails() {
		EntityManager mgr = fact.createEntityManager();
		try {
				String jpql = "select m from UserInfoBean m";
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
}
