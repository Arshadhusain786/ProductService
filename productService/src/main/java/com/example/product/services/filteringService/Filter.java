package com.example.product.services.filteringService;

import com.example.product.models.Product;

import java.util.List;

public interface Filter
{
    List<Product> apply(List<Product> products
    ,List<String> allowedValues);


}
