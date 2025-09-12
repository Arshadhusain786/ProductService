package com.example.product.dtos.products;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UpdateProductResponseDto
{
    private GetProductDto product;
    private String errorMessage;
}
