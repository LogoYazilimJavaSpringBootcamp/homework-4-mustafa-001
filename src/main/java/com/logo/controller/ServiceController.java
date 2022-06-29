package com.logo.controller;

import com.logo.model.RealWorldService;
import com.logo.service.RealWorldServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private RealWorldServiceService serviceService;

    @GetMapping
    public List<RealWorldService> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/name")
    public List<RealWorldService> getByName(@RequestParam String name) {
        return serviceService.getServicesStartingWith(name);
    }

    @GetMapping("/code/{code}")
    public RealWorldService getById(@PathVariable String code) {
        var service = serviceService.getServiceByCode(code);
        return service.orElse(null);
    }

    @PostMapping
    public RealWorldService add(@RequestBody RealWorldService service) {
        return serviceService.create(service);
    }

    @PutMapping("/{id}")
    public RealWorldService update(@RequestBody RealWorldService service, @PathVariable int id) {
        return serviceService.update(id, service);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        serviceService.delete(id);
    }


}
