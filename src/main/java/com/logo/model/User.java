package com.logo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.logo.model.enums.FirmType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
//Postgres doesn't accept "user" as tables name, hence custom name is necessary.
@Table(name = "isbasi_user")
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
	@OneToMany(mappedBy = "id")
	private List<Customer> customerList = new ArrayList<>(10);
	@OneToMany(mappedBy = "id")
	private Set<RealWorldService> serviceSet;
	@OneToMany(mappedBy = "id")
	private Set<Product> productSet;
	@OneToMany(mappedBy = "id")
	private Set<StockTransaction> stockTransactionSet;
	@OneToMany(mappedBy = "id")
	private Set<Invoice> invoiceSet;

}
