package com.example.product.services.filteringService;


import com.example.product.models.Product;

import java.util.List;

public class BrandFilter implements Filter
{
    @Override
    public List<Product> apply(List<Product> products, List<String> allowedValues) {
        return List.of();
    }
}
