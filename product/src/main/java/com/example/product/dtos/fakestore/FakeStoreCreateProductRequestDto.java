package com.example.product.dtos.fakestore;

import com.example.product.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductRequestDto
{
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;
    public static FakeStoreCreateProductRequestDto fromProduct(Product product)
    {
        FakeStoreCreateProductRequestDto fakeStoreCreateProductRequestDto = new FakeStoreCreateProductRequestDto();
        fakeStoreCreateProductRequestDto.title= product.getTitle();
        fakeStoreCreateProductRequestDto.category= product.getCategoryName();
        fakeStoreCreateProductRequestDto.image= product.getImageurl();
        fakeStoreCreateProductRequestDto.description= product.getDescription();
        fakeStoreCreateProductRequestDto.price= product.getPrice();

        return fakeStoreCreateProductRequestDto;

    }

}
