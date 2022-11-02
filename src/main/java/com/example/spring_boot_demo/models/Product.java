package com.example.spring_boot_demo.models;

import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

// POJO = Plain Object Java Object
@Entity
@Table(name = "product")
public class Product {
    // This is primary key
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO) // auto-increment
    // You can also use "sequence"
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1 // Increment by
                                                                                                        // 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")

    private Long id;
    // Validate = constraint
    @Column(nullable = false, unique = true, length = 300) // Column productName
    private String productName;
    private int year;
    private Double price;
    private String url;

    // Default constructor
    public Product() {
    }

    /*
     * Calculated field = transient, not exist in mysql
     * (những trường ko đc lưu trong CSDL, được tính toán từ các trường khác)
     */
    @Transient
    private int age; // age is calculated from "year"

    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - year;
    }

    public Product(String productName, int year, Double price, String url) {
        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id +
                ", productName='" + productName + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", url='" + url + '\'' +
                "}";

        // return "{" +
        // " id='" + getId() + "'" +
        // ", productName='" + getproductName() + "'" +
        // ", year='" + getYear() + "'" +
        // ", price='" + getPrice() + "'" +
        // ", url='" + getUrl() + "'" +
        // "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return year == product.year && age == product.age && Objects.equals(id, product.id)
                && Objects.equals(productName, product.productName) && Objects.equals(price, product.price)
                && Objects.equals(url, product.url);
    }

}
