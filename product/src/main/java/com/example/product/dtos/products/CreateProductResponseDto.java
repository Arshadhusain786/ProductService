package com.example.product.dtos.products;

import com.example.product.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResponseDto
{
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageurl;

    public static CreateProductResponseDto fromProduct(Product product)
    {
        CreateProductResponseDto createProductResponseDto = new CreateProductResponseDto();
        createProductResponseDto.setId(product.getId());
        createProductResponseDto.setImageurl(product.getImageurl());
        createProductResponseDto.setDescription(product.getDescription());
        createProductResponseDto.setPrice(product.getPrice());
        createProductResponseDto.setTitle(product.getTitle());

        return createProductResponseDto;

    }
}
