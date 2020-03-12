package com.capgemini.storesmanagementsystem.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("unused")
@Entity
@Data
@Table(name = "orders_info")
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int orderId;
	
	@Column(nullable = false)
	private String role;
	
	@Column(nullable = false)
	private double amount;
	
	@Column(nullable = false)
	private int quantity;
	
	@Column
	private String deliveredOn ;
	
	@Column
	private String dateOfOrder;
	
	@Column
	private String dateOfDelivery;
	
	@Column(nullable = false)
	private String productName;
	
	@Column
	private String status;
	
	@Column
	private int userId;
	
	@Column
	private int productId;
	
	@JsonIgnore
	@MapsId("userId")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId",referencedColumnName = "userId")
	private UserInfoBean user;
	
	@MapsId("productId")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="productId",referencedColumnName = "productId")
	private ProductInfoBean product ;
	
	
		
}
