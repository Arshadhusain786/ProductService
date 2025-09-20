package com.example.product.services;

import com.example.product.dtos.products.CreateProductDto;
import com.example.product.dtos.products.CreateProductRequestDto;
import com.example.product.exceptions.ProductNotFoundException;
import com.example.product.models.Product;

import java.util.List;

public interface ProductService
{
   Product createProduct(Product product);
   List<Product> getAllProducts();
   Product partialUpdateProduct(Long productId,Product product) throws ProductNotFoundException;
   Product getSingleProduct(Long productId) throws ProductNotFoundException;
   String deleteProduct(Long id);
   Product replaceProduct(Long id, CreateProductRequestDto requestDto);
}
