package com.occasion.maroc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.occasion.maroc.shared.dto.UserDto;

@Entity(name="products")
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = -3510752321445703119L;
	
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = true, length = 30)
	private String productId;
	
	@Column(nullable = true, length = 30)
	private String name;
	
	@Column(nullable = true, length = 30)
	private String photo;
	
	@Column(nullable = true, length = 30)
	private String category;
	
	@Column(nullable = true, length = 30)
	private String description;
	
	@Column(nullable = true, length = 30)
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	

}
