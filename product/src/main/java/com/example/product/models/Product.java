package com.example.product.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product
{
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageurl;
    private String categoryName;

}
