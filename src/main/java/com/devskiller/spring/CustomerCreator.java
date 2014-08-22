package com.devskiller.spring;

import com.devskiller.spring.model.Customer;
import com.devskiller.spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerCreator {

    private final CustomerRepository customerRepository;
    private final BarService bar;

    @Autowired
    public CustomerCreator(CustomerRepository customerRepository, BarService bar) {
        this.customerRepository = customerRepository;
        this.bar = bar;
    }

    @Transactional(rollbackFor = SomethingBadException.class)
    public void createCustomer(String firstName, String lastName) {
        System.out.println("");
        System.out.println("Creating customer " + lastName);
        customerRepository.save(new Customer(lastName));

        try {
            System.out.println("   update customer name to " + firstName);
            bar.updateName(firstName, lastName);
        } catch (SomethingBadException e) {
            System.err.println("Something wrong happened!");
        }

    }
}
