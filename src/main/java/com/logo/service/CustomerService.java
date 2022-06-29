package com.logo.service;

import com.logo.model.Customer;
import com.logo.model.SalesInvoice;
import com.logo.repository.CustomerRepository;
import com.logo.repository.SalesInvoiceRepository;
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
    private SalesInvoiceRepository salesInvoiceRepository;


    public CustomerService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Customer create(Customer request) {
        System.out.println("Adding customer:" + request.toString());
        request.setInvoiceList(salesInvoiceRepository.fetchInvoicesFromIds(request.getInvoiceList()));
        return customerRepository.save(request);
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

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public Customer update(int id, Customer customer) {
        System.out.println("Updating customer: " + id + "  to " + customer.toString());
        customer.setInvoiceList(salesInvoiceRepository.fetchInvoicesFromIds(customer.getInvoiceList()));
        var oldCustomerOpt = customerRepository.findById(id);
        if (oldCustomerOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }

        customer.setInvoiceList(salesInvoiceRepository.fetchInvoicesFromIds(customer.getInvoiceList()));

        var oldCustomer = oldCustomerOpt.get();
        if (customer.getName() != null) oldCustomer.setName(customer.getName());
        if (customer.getAge() != 0) oldCustomer.setAge(customer.getAge());
        if (customer.isActive() != oldCustomer.isActive()) oldCustomer.setActive(customer.isActive());
        if (customer.getInvoiceList() != null) oldCustomer.setInvoiceList(customer.getInvoiceList());
        return oldCustomer;
    }

    public void delete(int id) {
        System.out.println("Deleting customer: " + id);
        var customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        customerRepository.delete(customerOpt.get());
    }

}
