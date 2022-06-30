package com.logo.service;

import com.logo.model.RealWorldService;
import com.logo.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealWorldServiceService {
    @Autowired
   private ServiceRepository serviceRepository;

    public RealWorldService create(RealWorldService request){
        return serviceRepository.save(request);
    }

    public List<RealWorldService> getAllServices(){
        return serviceRepository.findAll();
    }

    public Optional<RealWorldService> getServiceByCode(String code){
        return  serviceRepository.findByServiceCode(code);
    }

    public List<RealWorldService> getServicesStartingWith(String searchQuery){
        return  serviceRepository.getServicesStartingWith(searchQuery);
    }

    public RealWorldService update(int id, RealWorldService service) {
        System.out.println("Updating service: " + id + "  to " + service.toString());
        var oldServiceOpt = serviceRepository.findById(id);
        if (oldServiceOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var oldService = oldServiceOpt.get();
        if (!service.getName().equals("")) {
            oldService.setName(service.getName());
        }
        if (!service.isActive() ==  oldService.isActive()) oldService.setActive(service.isActive());
        if (!service.getServiceCode().equals(oldService.getServiceCode())) oldService.setServiceCode(service.getServiceCode());
        if (!service.getCESSRate().equals(oldService.getCESSRate())) oldService.setCESSRate(service.getCESSRate());
        if (!service.getCurrency().equals(oldService.getCurrency())) oldService.setCurrency(service.getCurrency());
        if (!service.getPurchasePrice().equals(oldService.getPurchasePrice())) oldService.setPurchasePrice(service.getPurchasePrice());
        if (!service.getSalesPrice().equals(oldService.getSalesPrice())) oldService.setSalesPrice(service.getSalesPrice());
        if (!service.getUnitType().equals(oldService.getUnitType())) oldService.setUnitType(service.getUnitType());
        if (!service.getVatRate().equals(oldService.getVatRate())) oldService.setVatRate(service.getVatRate());
        return oldService;
    }

    public void delete(int id){
        System.out.println("Deleting service: " + id );
        var serviceOpt = serviceRepository.findById(id);
        if (serviceOpt.isEmpty()){
            throw new IllegalArgumentException();
        }
        serviceRepository.delete(serviceOpt.get());
    }

}
