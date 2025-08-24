package com.example.product.dtos.products;

import com.example.product.models.Category;
import com.example.product.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto
{
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageurl;
    private String category;

    public static CreateProductDto fromProduct(Product product)
    {
        CreateProductDto responseDto= new CreateProductDto();
        responseDto.setId(product.getId());
        responseDto.setImageurl(product.getImageurl());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setTitle(product.getTitle());

        return responseDto;

    }
    public Product toProduct()
    {
        Product product = new Product();
        product.setTitle(this.title);
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setImageurl(this.imageurl);
        Category category = new Category();
        product.setCategory(category);


        return product;
    }
}
