package com.logo.service;

import com.logo.model.Customer;
import com.logo.model.SalesInvoice;
import com.logo.repository.AddressRepository;
import com.logo.repository.CustomerRepository;
import com.logo.repository.SalesInvoiceRepository;
import com.logo.repository.customerdao.CustomerDao;
import com.logo.repository.customerdao.HibernateCustomerDao;
import com.logo.repository.customerdao.JDBCTemplateCustomerDao;
import com.logo.repository.customerdao.JdbcCustomerDao;
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
    private JdbcCustomerDao jdbcCustomerDao;
    @Autowired
    private JDBCTemplateCustomerDao jdbcTemplateCustomerDao;
    @Autowired
    private HibernateCustomerDao hibernateCustomerDao;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    private CustomerDao getCurrentCustomerDao() {
        int selection = 2;
        CustomerDao result = hibernateCustomerDao;
        if (selection == 0) {
            System.out.println("Using HibernateCustomerDao.");
            result = hibernateCustomerDao;
        } else if (selection == 1) {
            System.out.println("Using JDBCTemplateCustomerDao.");
            result = jdbcTemplateCustomerDao;
        } else if (selection == 2) {
            System.out.println("Using JdbcCustomerDao.");
            result = jdbcCustomerDao;
        }
        return result;
    }

    public CustomerService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Customer create(Customer request) {
        System.out.println("Adding customer:" + request.toString());
        var address = addressRepository.save(request.getAddress());
        request.setAddress(address);
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
        return getCurrentCustomerDao().findAll();
    }

    public Optional<Customer> getCustomerByName(String name) {
        return getCurrentCustomerDao().findByName(name);
    }

    public List<Customer> getByIsActive(boolean activeStatus) {
        return getCurrentCustomerDao().getByIsActive(activeStatus);
    }

    public Optional<Customer> getCustomerById(long id) {
        return getCurrentCustomerDao().findById(id);
    }

    public Customer update(long id, Customer customer) {
        System.out.println("Updating customer: " + id + "  to " + customer.toString());
        addressRepository.update(customer.getAddress());
        customer.setInvoiceList(salesInvoiceRepository.findAllById(customer.getInvoiceList().stream().map(SalesInvoice::getId).toList()));
        var oldCustomerOpt = getCurrentCustomerDao().findById(id);
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
        var customerOpt = getCurrentCustomerDao().findById(id);
        if (customerOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        getCurrentCustomerDao().delete(customerOpt.get());
    }

}
