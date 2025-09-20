package com.example.product.controllers;

import com.example.product.dtos.ErrorResponseDto;
import com.example.product.dtos.products.*;
import com.example.product.exceptions.ProductNotFoundException;
import com.example.product.models.Product;
import com.example.product.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{
    private RestTemplate restTemplate;
    private ProductService productService;
    public ProductController(@Qualifier("fakestoreProductservice") ProductService productService
    ,RestTemplate restTemplate)
    {
        this.productService=productService;
        this.restTemplate=restTemplate;
    }
    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestHeader("Authorization") String token,@RequestBody CreateProductRequestDto createProductRequestDto)
    {
        // you can only create product if you are logged in
        Boolean isAuthenticated = restTemplate.getForObject(
                "http://userService/auth/validate?token="+token,
                Boolean.class
        );
        if(!isAuthenticated)
        {
            return null;
        }
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
    public GetProductResponseDto getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getSingleProduct(id);

        GetProductResponseDto response = new GetProductResponseDto();
        response.setProduct(GetProductDto.from(product)); // convert entity → DTO

        return response;
    }
    @DeleteMapping("/{id}")
    public String  deleteProduct(@PathVariable Long id)
    {
        return productService.deleteProduct(id);
    }
    @PatchMapping("/{id}")
    public PatchProductResponseDto updateproduct(@PathVariable("id") Long productId ,
                                                 @RequestBody CreateProductDto productDto) throws ProductNotFoundException {
        Product product = productService.partialUpdateProduct(productId, productDto.toProduct());
        PatchProductResponseDto response = new PatchProductResponseDto();
        response.setProduct(GetProductDto.from(product));


        return response;
    }
    @PutMapping("/{id}")
    public GetProductResponseDto replaceProduct(
            @PathVariable Long id,
            @RequestBody CreateProductRequestDto requestDto) {

        Product updatedProduct = productService.replaceProduct(id,requestDto);

        GetProductResponseDto response = new GetProductResponseDto();
        response.setProduct(GetProductDto.from(updatedProduct));
        return response;
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
