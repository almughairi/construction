package com.open.construction;
import com.github.javafaker.Faker;
import com.open.construction.company.Business;
import com.open.construction.company.BusinessRepository;
import com.open.construction.company.License;
import com.open.construction.company.LicenseRepository;
import com.open.construction.customer.Customer;
import com.open.construction.customer.CustomerRepo;
import com.open.construction.data.Property;
import com.open.construction.data.PropertyType;
import com.open.construction.nationalid.NationalID;
import com.open.construction.nationalid.NationalIDRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
@RestController
@RequestMapping(path = "/api/v1/customers")
public class ConstructionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConstructionApplication.class, args);

	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepo customerRepo
	,LicenseRepository licenseRepository, BusinessRepository businessRepository, NationalIDRepository nationalIDRepository){
		return args -> {
			//generateValues(customerRepo);

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

			customer.addProperty(new Property(PropertyType.VILLA.toString(), 900.00,"Seeb", LocalDateTime.now()));
			customer.addProperty(new Property(PropertyType.VILLA.toString(), 650.00,"Seeb", LocalDateTime.now().minusDays(4)));
			customer.addProperty(new Property(PropertyType.BUILDING.toString(), 321.00,620.00,3,4,2,"Seeb", LocalDateTime.now().minusDays(4),customer));

			//customerRepo.save(customer);
			NationalID nationalID = new NationalID("12345678", customer);
			nationalIDRepository.save(nationalID);

			customerRepo.findById(1L).ifPresent(s->{
				System.out.println("Fetch my Properties lazy....");
				List<Property> propertys = customer.getProperties();
				propertys.forEach(property->{
					System.out.println(s.getFirstName() + "Owns " + property.getPlotSize());
				});
			});

			/*
			Business business = new Business(faker.company().name(), faker.address().fullAddress());
			License license = new License("9938397483", "Excellent", business);
			licenseRepository.save(license);
			businessRepository.findById(business.getId()).ifPresent(System.out::println);
			licenseRepository.findById(license.getId()).ifPresent(System.out::println);

			businessRepository.deleteById(business.getId());
			*/
		};
	}

	private void generateValues(CustomerRepo customerRepo){
		Faker faker = new Faker();
		for (int i = 0; i <= 5 ; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com",firstName,lastName);
			Customer customer = new Customer(
					email,
					faker.name().firstName(),
					faker.name().lastName(),
					faker.number().numberBetween(12,75)
			);
			customerRepo.save(customer);
		}
	}
}