package com.logo.model;

import com.logo.model.enums.UnitType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Currency;

//Common model for Product and Service in isbasi app.
//İşbaşı uygulamasındaki Ürün ve Hizmetler kısmındaki Ürün ve Hizmetler için ortak model.
@Getter
@Setter
@MappedSuperclass
abstract class ProductOrService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String name;
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
