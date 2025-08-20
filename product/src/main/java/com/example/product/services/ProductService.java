package com.example.product.services;

import com.example.product.dtos.CreateProductRequestDto;
import com.example.product.models.Product;

public interface ProductService
{
   Product createProduct(Product product);
}
