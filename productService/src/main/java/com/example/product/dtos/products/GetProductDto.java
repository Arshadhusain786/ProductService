package com.example.product.dtos.products;

import com.example.product.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductDto
{
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageurl;

    public static GetProductDto from(Product product) {
        GetProductDto getProductDto = new GetProductDto();
        getProductDto.setId(product.getId());
        getProductDto.setImageurl(product.getImageurl());
        getProductDto.setDescription(product.getDescription());
        getProductDto.setTitle(product.getTitle());
        getProductDto.setPrice(product.getPrice());
        return getProductDto;
    }
}
