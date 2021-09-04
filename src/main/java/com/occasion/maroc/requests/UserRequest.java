package com.occasion.maroc.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequest {
	
	@NotNull
	@Size(min =3)
	private String firstName;
	@NotNull
	@Size(min =3)
	private String lastName;
	@Email
	private String email;
	private String password;
	List<ProductRequests> products;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<ProductRequests> getProducts() {
		return products;
	}
	public void setProducts(List<ProductRequests> products) {
		this.products = products;
	}
	
	

}
