package com.example.product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity  // info about this table has to be stored in db
public class Product extends BaseModel
{
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageurl;

    // one category have many product
    @ManyToOne
    private Category category;

}
