package com.open.construction.customer;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(path="api/v1/customers")
public class CustomerController {

    public List<Customer> getALlCustomers(){

        String email2 = String.format("%s.%s@gmail.com", "Salim", "almughairi");
        Customer customer2 = new Customer(
                email2,
                "Salim",
                "Almughairi",
                46
        );

        List<Customer> customers = Arrays.asList(
            customer2
        );
        return customers;
    }

}
