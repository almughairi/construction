package com.open.construction.nationalid;
import com.open.construction.customer.Customer;
import jakarta.persistence.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name="NationalID")
@Table(name="nationalid", uniqueConstraints = {
        @UniqueConstraint(name="national_id_number_unique",columnNames = "civil_number")
})
public class NationalID {
    @Id
    @SequenceGenerator(name="national_id_sequence",sequenceName = "national_id_sequence",allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "national_id_sequence")
    @Column(name="id", updatable = false)
    private Long id;
    @Column(name="civil_number")
    private String civilNumber;
    @Column(name="expiry_date")
    private String expiryDate;
    @Column(name="id_image")
    private String idImage;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name="customer_id",
            referencedColumnName = "id"
    )
    private Customer customer;
    public NationalID() {
    }
    public NationalID(String civilNumber, String expiryDate, String idImage) {
        this.civilNumber = civilNumber;
        this.expiryDate = expiryDate;
        this.idImage = idImage;
    }

    public NationalID(String civilNumber, Customer customer) {
        this.civilNumber = civilNumber;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCivilNumber() {
        return civilNumber;
    }
    public void setCivilNumber(String civilNumber) {
        this.civilNumber = civilNumber;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String getIdImage() {
        return idImage;
    }
    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    @Override
    public String toString() {
        return "NationalID{" +
                "id=" + id +
                ", civilNumber='" + civilNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", idImage='" + idImage + '\'' +
                '}';
    }
}