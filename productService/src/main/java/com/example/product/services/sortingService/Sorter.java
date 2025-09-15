package com.example.product.services.sortingService;

import com.example.product.models.Product;

import java.util.List;

public interface Sorter
{
    List<Product> sort(List<Product>products);
}
