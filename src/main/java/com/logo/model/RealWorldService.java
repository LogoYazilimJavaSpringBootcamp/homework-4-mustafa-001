package com.logo.model;

import com.logo.model.enums.ServiceType;
import com.logo.model.enums.UnitType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;

//Models a service in isbasi app.
//İşbaşı uygulamasınının Stok ve Hizmet kısmındaki Servisler kısmını modeller.
@Data
public class RealWorldService extends ProductOrService {
    private String serviceCode;
}
