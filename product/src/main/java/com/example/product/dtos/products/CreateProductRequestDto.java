package com.example.product.dtos.products;

import com.example.product.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto
{
    private String title;
    private String description;
    private double price;
    private String imageurl;
    private String categoryName;

    public Product toProduct()
    {
        Product product = new Product();
        product.setTitle(this.title);
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setImageurl(this.imageurl);
        product.setCategoryName(this.categoryName);

        return product;
    }
}
