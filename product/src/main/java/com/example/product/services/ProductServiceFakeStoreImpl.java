package com.example.product.services;

import com.example.product.dtos.fakestore.FakeStoreCreateProductRequestDto;
import com.example.product.dtos.fakestore.FakeStoreGetProductResponseDto;
import com.example.product.dtos.products.CreateProductRequestDto;
import com.example.product.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//@Primary
@Service("fakestoreProductservice")
public class ProductServiceFakeStoreImpl implements ProductService
{
    private RestTemplate restTemplate;
    public ProductServiceFakeStoreImpl(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }
    @Override
    public Product createProduct(Product product)
    {
        FakeStoreCreateProductRequestDto fakeStoreCreateProductRequestDto=new FakeStoreCreateProductRequestDto();
        fakeStoreCreateProductRequestDto.setCategory(product.getCategory().getName());
        fakeStoreCreateProductRequestDto.setTitle(product.getTitle());
        fakeStoreCreateProductRequestDto.setImage(product.getImageurl());
        fakeStoreCreateProductRequestDto.setDescription(product.getDescription());
        fakeStoreCreateProductRequestDto.setPrice(product.getPrice());

        FakeStoreGetProductResponseDto response = restTemplate.postForObject("https://fakestoreapi.com/products",
              fakeStoreCreateProductRequestDto, FakeStoreGetProductResponseDto.class);


        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts()
    {
        throw new RuntimeException();
//        FakeStoreGetProductResponseDto[] response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                FakeStoreGetProductResponseDto[].class
//        );
//        List<FakeStoreGetProductResponseDto> responseDtoList= Stream.of(response).toList();
//        List<Product> products = new ArrayList<>();
//        for(FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto:responseDtoList)
//        {
//            products.add(fakeStoreGetProductResponseDto.toProduct());
//        }
//        return products;
    }

    @Override
    public Product partialUpdateProduct(Long productid, Product product) {
        HttpEntity<FakeStoreCreateProductRequestDto> requestEntity =
                new HttpEntity<>(FakeStoreCreateProductRequestDto.fromProduct(product));

        ResponseEntity<FakeStoreGetProductResponseDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productid,
                HttpMethod.PATCH,
                requestEntity,
                FakeStoreGetProductResponseDto.class
        );

        FakeStoreGetProductResponseDto productResponseDto = response.getBody();
        return  productResponseDto.toProduct();
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return restTemplate.getForObject(
                "https://fakestoreapi.com/products/{id}",
                Product.class,
                productId
        );
    }
    @Override
    public String deleteProduct(Long productId) {
        String url = "https://fakestoreapi.com/products/{id}";
        restTemplate.delete(url, productId);
        return "Product with ID " + productId + " deleted successfully!";
    }

    @Override
    public Product replaceProduct(Long id, CreateProductRequestDto requestDto) {
        String url = "https://fakestoreapi.com/products/{id}";

        return restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(requestDto), // request body
                Product.class,
                id
        ).getBody();
    }

}
