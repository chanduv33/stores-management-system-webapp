package com.capgemini.storesmanagementsystem.dto;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "dealer_prods")
public class DealerProductInfoBean {
	
	@Id
	@GeneratedValue
	@Column
	private int dealersProductId;
	
	@Column
	private double sellingPrice;
	
	@Column
	private int quantity;
	
	@Column
	private int minimumQuantity;
	
	@Column 
	private int dealerId;
	
	@Column
	private String imageUrl;
	
	@Column
	private String productName;
	
	@Column
	private int productId;
	
	@JsonIgnore
	@MapsId("dealerId")
	@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "dealerId",referencedColumnName = "userID")
	//@JoinTable(name = "dealer_products",joinColumns = @JoinColumn(name="dealerId"),
	//inverseJoinColumns = @JoinColumn(name="userId")  )
	@JoinColumn(name="dealerId",referencedColumnName = "userId")
	private UserInfoBean userProducts ;
	
	
	@MapsId("productId")
	@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "productId",referencedColumnName = "productId")
	//@JoinTable(name = "product_dealer",joinColumns = @JoinColumn(name="productId"),
	//inverseJoinColumns = @JoinColumn(name="productId")  )
	@JoinColumn(name="productId",referencedColumnName = "productId")
	private ProductInfoBean prod;
	
}
