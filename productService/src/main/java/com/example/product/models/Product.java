package com.example.product.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity  // info about this table has to be stored in db
public class Product extends BaseModel
{

    private String title;
    private String description;
    private Double price;
    private String imageurl;

    // one category have many product
    @ManyToOne(cascade={CascadeType.PERSIST})
    private Category category;

}
