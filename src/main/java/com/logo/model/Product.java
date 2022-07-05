package com.logo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.logo.model.enums.UnitType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

//Common model for Product and Service in isbasi app.
//İşbaşı uygulamasındaki Ürün ve Hizmetler kısmındaki Ürün ve Hizmetler için ortak model.
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String name;
    @JoinColumn
    @JsonBackReference
    @ManyToOne
    private User user;
    private String barcode;
    //TODO Not sure how exactly this is calculated.
    private Double reserveExcludedAmount;

     UnitType unitType;
    //KDV(Katma Değer Vergisi)/VAT(Value Added Tax)
    protected BigDecimal vatRate;
    protected Currency currency;
    //without VAT
    protected BigDecimal salesPrice;
    //without VAT
    protected BigDecimal purchasePrice;
    //tevkifat
    protected BigDecimal withholdingRatePercent;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    protected boolean isActive;
    //ÖTV, ÖİV or similar other taxes.
    protected BigDecimal CESSRate;
}
