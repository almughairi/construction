package com.open.construction.company;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;
@Entity(name="Business")
@Table(name="business")
public class Business {

        @Id
        @SequenceGenerator(name="business_sequence",sequenceName = "business_sequence", allocationSize = 10)
        @GeneratedValue(strategy = SEQUENCE, generator = "business_sequence")
        @Column(
                name="id",
                updatable = false
        )
        private Long id;
        @Column(name="business_name", nullable = false)
        private String businessName;
        @Column(name="location")
        private String location;

        @OneToOne(
                mappedBy = "business",
                orphanRemoval = true
        )
        private License license;

        public Business(String companyName, String location) {
            this.businessName = companyName;
            this.location = location;
        }

    public Business() {
    }

    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCompanyName() {
            return businessName;
        }

        public void setCompanyName(String companyName) {
            this.businessName = companyName;
        }


        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
