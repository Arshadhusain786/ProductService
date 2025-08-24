package com.example.product.services;

import com.example.product.dtos.products.CreateProductRequestDto;
import com.example.product.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService
{
    @Override
    public Product createProduct(Product product)
    {
        return  null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product partialUpdateProduct(Long productid,Product product) {
        return null;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public String deleteProduct(Long id) {
        return "";
    }

    @Override
    public Product replaceProduct(Long id, CreateProductRequestDto requestDto) {
        return null;
    }
}
