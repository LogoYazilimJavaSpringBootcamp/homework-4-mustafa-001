package com.logo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.logo.model.enums.FirmType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Setter
@Getter
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

}
