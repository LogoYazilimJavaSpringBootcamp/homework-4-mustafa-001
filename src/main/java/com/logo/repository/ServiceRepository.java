package com.logo.repository;

import com.logo.model.RealWorldService;
import com.logo.model.SalesInvoice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ServiceRepository {
   private static final List<RealWorldService> serviceList = new ArrayList<>();
   private static int nextId = 0;

   public RealWorldService save(RealWorldService service){
       service.setId(nextId);
       nextId +=1;
       serviceList.add(service);
       return service;
   }
   public Optional<RealWorldService> findById(int id){
        return serviceList.stream().filter(it -> it.getId() == id).findFirst();
    }

    public List<RealWorldService> getAll() {
        return serviceList;
    }

    public void  delete(RealWorldService service){
        serviceList.remove(service);
    }

    public Optional<RealWorldService> findByCode(String code) {
       return serviceList.stream().filter(it -> it.getServiceCode().equals(code)).findFirst();
    }

    public List<RealWorldService> getServicesStartingWith(String searchQuery) {
       return serviceList.stream().filter(it -> it.getName().startsWith(searchQuery)).toList();
    }

    //Client only need to send Service Id to refer to existing service.
    //We replace service list that came from user with our existing service entities.
    public List<RealWorldService> fetchServicesFromIds(List<RealWorldService> emptyServices) {
        return emptyServices.stream()
                .map(it -> this.findById(it.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}

