package com.logo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.logo.model.enums.FirmType;
import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
//Postgres doesn't accept "user" as tables name, hence custom name is necessary.
@Table(name = "IsbasıUser")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String surname;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private FirmType firmType;
	@OneToOne
	private Address address;
	@OneToMany
	private List<Customer> customerList = new ArrayList<>(10);
	@OneToMany
	private Set<RealWorldService> serviceSet;
	@OneToMany
	private Set<Product> productSet;
	@OneToMany Set<StockTransaction> stockTransactionSet;
	@OneToMany Set<SalesInvoice> salesInvoiceSet;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public FirmType getFirmType() {
		return firmType;
	}

	public void setFirmType(FirmType firmType) {
		this.firmType = firmType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	
	

}
