package com.devskiller.spring;

import com.devskiller.spring.model.Customer;
import com.devskiller.spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BarService {

    private final CustomerRepository customerRepository;

    @Autowired
    public BarService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(rollbackFor = SomethingBadException.class)
    public void updateName(String firstName, String lastName) throws SomethingBadException {
        System.out.println("   reading customer for update... ");
        List<Customer> byFirstNameAndLastName = customerRepository.findByLastName(lastName);

        if(byFirstNameAndLastName.size() != 1){
            throw new SomethingBadException();
        }
        Customer customer = byFirstNameAndLastName.get(0);
        customer.setFirstName(firstName);

        System.out.println("   saving... ");
        customerRepository.save(customer);
    }
}
