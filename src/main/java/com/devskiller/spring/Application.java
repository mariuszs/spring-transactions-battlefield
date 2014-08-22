package com.devskiller.spring;

import com.devskiller.spring.model.Customer;
import com.devskiller.spring.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        CustomerRepository repository = context.getBean(CustomerRepository.class);

        CustomerCreator creator = context.getBean(CustomerCreator.class);

        createCustomer(creator, "Jan", "Kowalski");
        createCustomer(creator, "Karol", "Nowak");
        createCustomer(creator, "Andrzej", "Nowak"); // error - second Nowak!
        createCustomer(creator, "John", "Smith");

        System.out.println("--------------------------------------------");
        List<Customer> customers = (List<Customer>) repository.findAll();
        System.out.println("--------------------------------------------");
        for (Customer customer : customers) {
            System.out.println(customer);
        }

        assert customers.size() == 3;

        context.close();

    }

    private static void createCustomer(CustomerCreator creator, String jan, String lastName) {
        try {
            creator.createCustomer(jan, lastName);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
