package com.logo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.logo.model.enums.FirmType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
//Postgres doesn't accept "user" as tables name, hence custom name is necessary.
@Table(name = "isbasi_user")
@NoArgsConstructor
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
	@JsonManagedReference
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@OneToMany(mappedBy = "user")
	private List<Customer> customerList = new ArrayList<>();
	@JsonManagedReference
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@OneToMany(mappedBy = "user")
	private Set<Product> productSet = new HashSet<>();
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Set<StockTransaction> stockTransactionSet = new HashSet<>();
	@JsonManagedReference
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@OneToMany(mappedBy = "user")
	private Set<Invoice> invoiceSet = new HashSet<>();

}
