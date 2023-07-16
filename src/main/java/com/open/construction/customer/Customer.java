package com.open.construction.customer;


import com.open.construction.data.Property;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
        name="customer",
        uniqueConstraints = {
                @UniqueConstraint(name="customer_email_unique",columnNames = "email")
        }
)
public class Customer {
    @Id
    @SequenceGenerator(name="customer_sequence",sequenceName = "customer_sequence",allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "customer_sequence")
    @Column(name="id", updatable = false)
    private Long id;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="first_name",nullable = false,columnDefinition = "TEXT")
    private String firstName;
    @Column(name="last_name",columnDefinition = "TEXT")
    private String lastName;

    @Column(name="age")
    private Integer age;


    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true, //when ever we want to remove a customer we also want to remove all the properties
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, // that is why we have this cascade type.
            fetch = FetchType.LAZY
    )
    private List<Property> properties = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long id, String email, String firstName, String lastName, Integer age) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Customer(String email, String firstName, String lastName, Integer age) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void addProperty(Property property){
        if(!this.properties.contains(property)){
            this.properties.add(property);
            property.setCustomer(this);
        }
    }

    public void removeProperty(Property property){
        if(this.properties.contains(property)){
            this.properties.remove(property);
            property.setCustomer(null);
        }
    }

    public List<Property> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

}
