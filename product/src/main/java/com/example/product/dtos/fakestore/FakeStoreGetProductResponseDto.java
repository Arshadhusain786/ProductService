package com.example.product.dtos.fakestore;

import com.example.product.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreGetProductResponseDto
{
    private Long id;
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;

    public Product toProduct()
    {
        Product product = new Product();
        product.setTitle(this.title);
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setImageurl(this.image);
        product.setCategoryName(this.category);

        return  product;
    }


}
