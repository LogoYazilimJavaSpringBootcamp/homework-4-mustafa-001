package com.logo.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;

//Models a service in isbasi app.
//İşbaşı uygulamasınının Stok ve Hizmet kısmındaki Servisler kısmını modeller.
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class RealWorldService extends ProductOrService {
    private String serviceCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RealWorldService that = (RealWorldService) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
