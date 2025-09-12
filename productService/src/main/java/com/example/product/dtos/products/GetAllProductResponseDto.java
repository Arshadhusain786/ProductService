package com.example.product.dtos.products;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllProductResponseDto
{
    private List<GetProductDto> productDtos;
}
