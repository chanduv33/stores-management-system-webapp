package com.capgemini.storesmanagementsystem.dao;

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

@Log
@Repository
public class ManufacturerDAOImpl implements ManufacturerDAO {

	@PersistenceUnit
	private EntityManagerFactory fact ;

	@Override
	public boolean setCostPrice(ProductInfoBean bean) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			ProductInfoBean setProd=mgr.find(ProductInfoBean.class, bean.getProductId());
			setProd.setProductCost(bean.getProductCost());		
			mgr.persist(setProd);
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
	public List<OrderDetails> getPaymentDetails(int userId) {
		EntityManager mgr = fact.createEntityManager();
		try {
			@SuppressWarnings("unchecked")
			TypedQuery<OrderDetails> query = (TypedQuery<OrderDetails>) mgr.createNativeQuery("SELECT o.* FROM product_info d RIGHT OUTER JOIN orders_info o ON d.productId"
					+ " = o.productId WHERE o.role='ROLE_DEALER' AND d.userId="+userId, OrderDetails.class);
			List<OrderDetails> orders = query.getResultList();
			if(orders != null) {
				return orders;
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
	public boolean addProduct(UserInfoBean manufacturer) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			UserInfoBean bean = mgr.find(UserInfoBean.class, manufacturer.getUserId());
			Iterator<ProductInfoBean> itr = manufacturer.getProducts().iterator();
			if(itr.hasNext()) {
				ProductInfoBean cameProd = itr.next();
				ProductInfoBean product = new ProductInfoBean();
				product.setManufacturerName(bean.getName());
				product.setImageUrl(cameProd.getImageUrl());
				product.setProductName(cameProd.getProductName());
				product.setProductCost(cameProd.getProductCost());
				product.setQuantity(cameProd.getQuantity());
				product.setDescription(cameProd.getDescription());
				product.setUsers(bean);
				bean.getProducts().add(product);
				mgr.persist(bean);
			} else {
				System.out.println("Products Not Found");
			}
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
	public List<ProductInfoBean> getAllProducts(int userId) {
		EntityManager mgr = fact.createEntityManager();
		try {
			@SuppressWarnings("unchecked")
			TypedQuery<ProductInfoBean> query = (TypedQuery<ProductInfoBean>)  mgr.createNativeQuery("select * from product_info where userId="+userId, ProductInfoBean.class);
				List<ProductInfoBean> beans = (List<ProductInfoBean>)query.getResultList();
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
	public boolean updateProduct(ProductInfoBean bean) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			ProductInfoBean setProd=mgr.find(ProductInfoBean.class, bean.getProductId());
			setProd.setProductName(bean.getProductName());
			setProd.setImageUrl(bean.getImageUrl());
			mgr.persist(setProd);
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
	public boolean removeProduct(int productId) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			ProductInfoBean bean = mgr.find(ProductInfoBean.class, productId);
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
