package com.logo.model;

import lombok.*;

import javax.persistence.*;

//Models a product in isbasi app.
//İşbaşı uygulamasınının Stok ve Hizmet kısmındaki Ürünler kısmını modeller.
@Getter
@Setter
@Entity
public class Product extends ProductOrService {
	private String barcode;

	//TODO Not sure how exactly this is calculated.
	private Double reserveExcludedAmount;
}
