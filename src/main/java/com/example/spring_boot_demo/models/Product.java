package com.example.spring_boot_demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// POJO = Plain Object Java Object
@Entity
public class Product {
    // This is primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto-increment
    private Long id;
    private String productName;
    private int year;
    private Double price;
    private String url;

    // Default constructor
    public Product() {
    }

    public Product(Long id, String productName, int year, Double price, String url) {
        this.id = id;
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

}
