package com.capgemini.storesmanagementsystem.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.storesmanagementsystem.dto.*;

import lombok.extern.java.Log;

@Log
@Repository
public class DealerDAOImpl implements DealerDAO {

	@PersistenceUnit
	private EntityManagerFactory fact ;
	
	@Override
	public OrderDetails placeOrder( UserInfoBean dealer) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		UserInfoBean user = new UserInfoBean();
		ProductInfoBean prods = new ProductInfoBean();
		DealerProductInfoBean dealerProds = new DealerProductInfoBean();
		try {
			tx.begin();
			UserInfoBean bean = mgr.find(UserInfoBean.class, dealer.getUserId());
			Iterator<DealerProductInfoBean> itr = dealer.getDealersProds().iterator();
			LocalDate date = LocalDate.now();
			if (itr.hasNext()) {
				DealerProductInfoBean product = itr.next();
				ProductInfoBean prod = mgr.find(ProductInfoBean.class, product.getProductId());
				dealerProds.setDealerId(bean.getUserId());
				user.setRole(bean.getRole());
				user.setUserId(bean.getUserId());
				dealerProds.setProductId(prod.getProductId());
				dealerProds.setQuantity(product.getQuantity());
				dealerProds.setProductName(prod.getProductName());
				dealerProds.setImageUrl(prod.getImageUrl());
				bean.getDealersProds().add(dealerProds);
				OrderDetails order = new OrderDetails();
				order.setUserId(user.getUserId());
				order.setDateOfOrder(date.toString());
				prods.setProductId(prod.getProductId());
				order.setProductId(prod.getProductId());
				order.setProductName(prod.getProductName());
				order.setStatus("Not yet Delivered");
				order.setAmount(prod.getProductCost()*product.getQuantity());
				order.setRole(user.getRole());
				order.setQuantity(product.getQuantity());
				order.setDateOfDelivery(date.plusDays(2).toString());
				bean.getOrders().add(order);
				//mgr.persist(order);
				mgr.persist(bean);
				mgr.flush();
				tx.commit();
				String jpql = "select o from OrderDetails o order by o.orderId desc";
				Query query =  mgr.createQuery(jpql,OrderDetails.class);
				OrderDetails placedOrder = (OrderDetails) query.setMaxResults(1).getSingleResult();
				mgr.close();
				return placedOrder;
			}else {
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
	public boolean setSellingPrice(DealerProductInfoBean dealer) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
		tx.begin();
		DealerProductInfoBean bean = mgr.find(DealerProductInfoBean.class, dealer.getDealersProductId());
		bean.setSellingPrice(dealer.getSellingPrice());
		mgr.persist(bean);
		tx.commit();
		return true;
		} catch(Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				log.info(ele.toString());
				return false;
			}
		}
		return false;
	}

	@Override
	public DealerProductInfoBean getProduct(int id) {
		EntityManager mgr = fact.createEntityManager();
		try {
		DealerProductInfoBean bean = mgr.find(DealerProductInfoBean.class, id);
		if(bean!=null)
		return bean;
		else {
			return null;
		}
		} catch(Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				log.info(ele.toString());
				return null;
			}
		}
		return null;
	}


	@Override
	public OrderDetails getPaymentDeatils(int orderId) {
		EntityManager mgr = fact.createEntityManager();
		OrderDetails order = mgr.find(OrderDetails.class, orderId);
		if(order!=null) {
			return order;
		} else {
			return null;
		}
	}


	@Override
	public boolean checkNameAvailability(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setDeliveredDate(OrderDetails order) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
		tx.begin();
		OrderDetails bean = mgr.find(OrderDetails.class, order.getOrderId());
		bean.setDeliveredOn(order.getDeliveredOn());
		bean.setStatus("Delivered");
		mgr.persist(bean);
		tx.commit();
		return true;
		} catch(Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				log.info(ele.toString());
				return false;
			}
		}
		return false;
	}

	@Override
	public List<DealerProductInfoBean> getAllProducts(int userId) {
		EntityManager mgr = fact.createEntityManager();
		String jpql = "select e from DealerProductInfoBean e";
		try {
		TypedQuery<DealerProductInfoBean> query = mgr.createQuery(jpql, DealerProductInfoBean.class);
		List<DealerProductInfoBean> prods = query.getResultList();
		if(prods!=null)
		return prods;
		else 
			return null;
		} catch(Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				log.info(ele.toString());
				return null;
			}
		}
		return null;
	}

	@Override
	public boolean setMinimumQuantity(DealerProductInfoBean dealer) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
		tx.begin();
		DealerProductInfoBean bean = mgr.find(DealerProductInfoBean.class, dealer.getDealersProductId());
		bean.setMinimumQuantity(dealer.getMinimumQuantity());
		mgr.persist(bean);
		tx.commit();
		return true;
		} catch(Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				log.info(ele.toString());
				return false;
			}
		}
		return false;
	}
	
}
