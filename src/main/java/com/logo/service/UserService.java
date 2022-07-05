package com.logo.service;

import java.util.List;
import java.util.Optional;

import com.logo.dto.EmailDto;
import com.logo.model.Address;
import com.logo.repository.AddressRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logo.model.Customer;
import com.logo.model.User;
import com.logo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public User createUser(User request) {
        try {
            rabbitTemplate.convertAndSend("isbasi.email", new EmailDto(request.getEmail(), request.getName(), "isbasi uygulamasına hoşgeldiniz.",
                    "Patika eğitimi bünyesinde geliştirilen işbaşı uygulamasına kaydeoldunuz."));
            rabbitMQService.sendEmail(request.getEmail());
        } catch (Exception c) {
            System.out.println("RabbitMQ connection refused. Continuing.");
        }

        request.setAddress(addressRepository.save(request.getAddress()));
        return userRepository.save(request);
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        //kullanıcı bulunamadığında hata verilmeli

        //userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException());

        //userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        boolean isPresent = userRepository.findByEmail(email).isPresent();
        if (isPresent) {
            return userRepository.findByEmail(email).get();
        }
        // null dönme
        return null;

    }

    public List<Customer> getCustomersByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);

        if (foundUser.isPresent()) {
            return foundUser.get().getCustomerList();
        }

        return null;
    }

}
