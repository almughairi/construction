package com.open.construction.data;


import com.open.construction.customer.Customer;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Property {

    @Id
    @SequenceGenerator(
            name="property_id_sequence",
            sequenceName = "property_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "property_id_sequence"
    )
    @Column(name="id",updatable = false) //updatable=false means that once it is created can not be updated.
    private Long id;
    @Column(name="type")
    private String type;
    @Column(name="plot_size")
    private Double plotSize;
    @Column(name="build_up_area", nullable = true)
    private Double buildUpArea;
    @Column(name="bed_rooms")
    private Integer bedRooms;
    @Column(name="toilet")
    private Integer toilet;
    @Column(name="kitchen")
    private Integer kitchen;
    @Column(
            name="location",
            columnDefinition = "TEXT"
    )
    private String location;
    @Column(
            name="created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(
            name="customer_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="customer_property_fk"
            )
    )
    private Customer customer;
    public Property() {
    }
    public Property(String type, Double plotSize, String location, LocalDateTime createdAt) {
        this.type = type;
        this.plotSize = plotSize;
        this.location = location;
        this.createdAt = createdAt;
    }
    public Property(
                    String type,
                    Double plotSize,
                    Double buildUpArea,
                    Integer bedRooms,
                    Integer toilet,
                    Integer kitchen,
                    String location,
                    LocalDateTime createdAt,
                    Customer customer) {

        this.type = type;
        this.plotSize = plotSize;
        this.buildUpArea = buildUpArea;
        this.bedRooms = bedRooms;
        this.toilet = toilet;
        this.kitchen = kitchen;
        this.location = location;
        this.createdAt = createdAt;
        this.customer = customer;
    }
    public Long getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Double getBuildUpArea() {
        return buildUpArea;
    }
    public void setBuildUpArea(Double buildUpArea) {
        this.buildUpArea = buildUpArea;
    }
    public Integer getBedRooms() {
        return bedRooms;
    }
    public Double getPlotSize() {
        return plotSize;
    }
    public void setPlotSize(Double plotSize) {
        this.plotSize = plotSize;
    }
    public Integer getKitchen() {
        return kitchen;
    }
    public void setKitchen(Integer kitchen) {
        this.kitchen = kitchen;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setBedRooms(Integer bedRooms) {
        this.bedRooms = bedRooms;
    }
    public Integer getToilet() {
        return toilet;
    }
    public void setToilet(Integer toilet) {
        this.toilet = toilet;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", type=" + type +
                ", plotSize=" + plotSize +
                ", buildUpArea=" + buildUpArea +
                ", bedRooms=" + bedRooms +
                ", toilet=" + toilet +
                ", kitchen=" + kitchen +
                ", location='" + location + '\'' +
                ", createdAt=" + createdAt +
                ", customer=" + customer +
                '}';
    }
}
