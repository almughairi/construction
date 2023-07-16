package com.open.construction.company;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name="License")
@Table(name="license",
        uniqueConstraints = {
            @UniqueConstraint(name="license_unique",columnNames = "cr_number")
        }
)
public class License {
    @Id
    @SequenceGenerator(name="license_sequence",sequenceName = "license_sequence", allocationSize = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "license_sequence")
    @Column(
            name="id",
            updatable = false
    )
    private Long id;
    @Column(name="cr_number", nullable = false)
    private String crNumber;
    @Column(name="company_grade")
    private String companyGrade;



    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name="business_id", // in the license table the Foreign key will be called business_id.
            referencedColumnName = "id", // this id is the primary key of the Business table.
            // modify the constraint of a foreign key
            foreignKey = @ForeignKey(name="license_id_business_id_fk")
    )
    private Business business; // we don't want to delete the business if we delete the license.
    public License() {
    }


    public License(String crNumber, String companyGrade, Business business) {
        this.crNumber = crNumber;
        this.companyGrade = companyGrade;
        this.business = business;
    }

    public Long getId() {
        return id;
    }

    public String getCrNumber() {
        return crNumber;
    }

    public String getCompanyGrade() {
        return companyGrade;
    }

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", crNumber='" + crNumber + '\'' +
                ", companyGrade='" + companyGrade + '\'' +
                ", business=" + business +
                '}';
    }
}
