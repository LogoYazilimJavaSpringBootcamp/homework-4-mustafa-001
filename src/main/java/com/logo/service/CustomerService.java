package com.logo.service;

import com.logo.model.Customer;
import com.logo.model.SalesInvoice;
import com.logo.repository.AddressRepository;
import com.logo.repository.CustomerRepository;
import com.logo.repository.SalesInvoiceRepository;
import com.logo.repository.customerdao.CustomerDao;
import com.logo.repository.customerdao.HibernateCustomerDao;
import com.logo.repository.customerdao.JDBCTemplateCustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HibernateCustomerDao hibernateCustomerDao;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    private CustomerDao getCurrentCustomerDao(){
        if (true) {
            System.out.println("Using HibernateCustomerDao.");
            return hibernateCustomerDao;
        } else if (false){
            System.out.println("Using JDBCTemplateCustomerDao.");
        }
        return null;
    }

    public CustomerService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Customer create(Customer request) {
        System.out.println("Adding customer:" + request.toString());
        addressRepository.save(request.getAddress());
        request.setInvoiceList(salesInvoiceRepository.findAllById(request.getInvoiceList().stream().map(SalesInvoice::getId).toList()));
        return getCurrentCustomerDao().save(request);
    }

    public List<Customer> getAllCustomers() {

        // ProductService productService = new ProductService;
        // singleton olduğunun kanıtı
        System.out.println("CustomerService - productService:" + productService.toString());
        System.out.println("CustomerService - orderService:" + orderService.toString());

//		orderService.createOrder();

//		return prepareCustomerList();
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    public List<Customer> getByIsActive(boolean activeStatus) {
        return customerRepository.getByIsActive(activeStatus);
    }

    public Optional<Customer> getCustomerById(long id) {
        return customerRepository.findById(id);
    }

    public Customer update(long id, Customer customer) {
        System.out.println("Updating customer: " + id + "  to " + customer.toString());
        addressRepository.update(customer.getAddress());
        customer.setInvoiceList(salesInvoiceRepository.findAllById(customer.getInvoiceList().stream().map(SalesInvoice::getId).toList()));
        var oldCustomerOpt = customerRepository.findById(id);
        if (oldCustomerOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }

        customer.setInvoiceList(salesInvoiceRepository.findAllById(customer.getInvoiceList().stream().map(SalesInvoice::getId).toList()));

        var oldCustomer = oldCustomerOpt.get();
        if (customer.getName() != null) oldCustomer.setName(customer.getName());
        if (customer.getAge() != 0) oldCustomer.setAge(customer.getAge());
        if (customer.isActive() != oldCustomer.isActive()) oldCustomer.setActive(customer.isActive());
        if (customer.getInvoiceList() != null) oldCustomer.setInvoiceList(customer.getInvoiceList());
        return oldCustomer;
    }

    public void delete(long id) {
        System.out.println("Deleting customer: " + id);
        var customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        customerRepository.delete(customerOpt.get());
    }

}
