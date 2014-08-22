package com.devskiller.spring.repository;

import com.devskiller.spring.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Mariusz Smykuła
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

}
