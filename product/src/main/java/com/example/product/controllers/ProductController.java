package com.example.product.controllers;

import com.example.product.dtos.ErrorResponseDto;
import com.example.product.dtos.products.*;
import com.example.product.models.Product;
import com.example.product.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/all")
    public GetAllProductResponseDto getAllProducts()
    {
      List<Product> products = productService.getAllProducts();
      GetAllProductResponseDto response = new GetAllProductResponseDto();
      List<GetProductDto> getProductDtos = new ArrayList<>();
      for(Product p: products)
      {
         getProductDtos.add(GetProductDto.from(p));
      }
      response.setProductDtos(getProductDtos);
      return response;
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
    @PatchMapping("/{id}")
    public PatchProductResponseDto updateproduct(@PathVariable Long productId ,
                                                 @RequestBody CreateProductDto productDto)
    {
        Product product = productService.partialUpdateProduct(productId, productDto.toProduct());
        PatchProductResponseDto response = new PatchProductResponseDto();
        response.setProduct(GetProductDto.from(product));


        return response;
    }
    public void replaceProduct()
    {

    }
//    @ExceptionHandler(RuntimeException.class)
//    public ErrorResponseDto handleRuntimeException(RuntimeException e)
//    {
//        ErrorResponseDto dto = new ErrorResponseDto();
//        dto.setMessage(e.getMessage());
//        dto.setStatus("ERROR");
//        return dto;
//    }
//    // if we found any exception in this controller this method gets called
//    @ExceptionHandler(Exception.class)
//    public ErrorResponseDto handleException(Exception e)
//    {
//        ErrorResponseDto dto = new ErrorResponseDto();
//        dto.setMessage(e.getMessage());
//        dto.setStatus("ERROR");
//       return dto;
//    }
//    @RequestMapping(name="Arshad",value ="/products/")
//    public String method()
//    {
//        return "magic";
//    }
}
