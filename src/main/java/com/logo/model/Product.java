package com.logo.model;

import lombok.*;

//Models a product in isbasi app.
//İşbaşı uygulamasınının Stok ve Hizmet kısmındaki Ürünler kısmını modeller.
@Data
public class Product extends ProductOrService {
	private String barcode;

	//TODO Not sure how exactly this is calculated.
	private Double reserveExcludedAmount;
}
