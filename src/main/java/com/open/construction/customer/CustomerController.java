package com.open.construction.customer;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(path="api/v1/customers")
public class CustomerController {
@GetMapping
    public List<Customer> getALlCustomers(){
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = String.format("%s.%s@gmail.com",firstName,lastName);
    Customer customer = new Customer(
            email,
            faker.name().firstName(),
            faker.name().lastName(),
            faker.number().numberBetween(12,75)
    );

        List<Customer> customers = Arrays.asList(
            customer

        );
        return customers;
    }

}
