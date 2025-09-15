package com.example.product.dtos.search;

import com.example.product.dtos.products.GetProductDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class SearchResponseDto
{
    private Page<GetProductDto> productsPage;



}
