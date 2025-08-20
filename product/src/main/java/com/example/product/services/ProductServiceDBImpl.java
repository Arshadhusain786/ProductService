package com.example.product.services;

import com.example.product.models.Product;
import org.springframework.stereotype.Service;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService
{
    @Override
    public Product createProduct(Product product)
    {
        return  null;
    }


}
