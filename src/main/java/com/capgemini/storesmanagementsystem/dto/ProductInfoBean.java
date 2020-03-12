package com.capgemini.storesmanagementsystem.dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "product_info")
public class ProductInfoBean {
	
	@Id
	@GeneratedValue
	@Column
	private int productId;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private double productCost;
	
	
	
	@Column
	private String imageUrl;
	
	@Column
	private int quantity;
	
	@Column
	private int userId;
	
	@JsonIgnore
	@MapsId("userId")
	@ManyToOne
	@JoinColumn(name = "userId",referencedColumnName = "userId")
	@EqualsAndHashCode.Exclude 
	private UserInfoBean users ;
}
