package com.example.product.controllers;

import com.example.product.dtos.CreateProductRequestDto;
import com.example.product.dtos.CreateProductResponseDto;
import com.example.product.models.Product;
import com.example.product.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController
{
    private ProductService productService;
    public ProductController(@Qualifier("fakestoreProductservice") ProductService productService)
    {
        this.productService=productService;
    }
    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto)
    {
        // converting request dto to entity
        Product product = productService.createProduct(createProductRequestDto.toProduct());

        // converting entity to response dto
        return CreateProductResponseDto.fromProduct(product);
        //return "The price of this product is: "+ createProductRequestDto.getPrice();
    }
    @GetMapping("")
    public void getAllProducts()
    {

    }
    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable("id") Long id)
    {
        return "here is your product"+id;

    }
    @DeleteMapping("/{id}")
    public void deleteProduct()
    {

    }
    @PutMapping("/{id}")
    public void updateproduct()
    {

    }
    public void replaceProduct()
    {

    }
//    @RequestMapping(name="Arshad",value ="/products/")
//    public String method()
//    {
//        return "magic";
//    }
}
