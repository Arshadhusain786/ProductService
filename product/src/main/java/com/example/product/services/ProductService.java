package com.example.product.services;

import com.example.product.models.Product;

import java.util.List;

public interface ProductService
{
   Product createProduct(Product product);
   List<Product> getAllProducts();
   Product partialUpdateProduct(Long productid,Product product);
}
