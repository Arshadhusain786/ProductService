package com.example.product.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity  // info about this table has to be stored in db
public class Product extends BaseModel {

    private String title;
    private String description;
    private Double price;
    private String imageurl;

    // One category can have many products
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JsonBackReference  // ✅ Prevents infinite loop when serializing
    private Category category;
}
