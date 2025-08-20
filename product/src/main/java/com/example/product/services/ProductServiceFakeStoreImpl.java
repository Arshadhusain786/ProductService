package com.example.product.services;

import com.example.product.dtos.FakeStoreCreateProductRequestDto;
import com.example.product.dtos.FakeStoreCreateProductResponseDto;
import com.example.product.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        fakeStoreCreateProductRequestDto.setCategory(product.getCategoryName());
        fakeStoreCreateProductRequestDto.setTitle(product.getTitle());
        fakeStoreCreateProductRequestDto.setImage(product.getImageurl());
        fakeStoreCreateProductRequestDto.setDescription(product.getDescription());
        fakeStoreCreateProductRequestDto.setPrice(product.getPrice());

        FakeStoreCreateProductResponseDto response = restTemplate.postForObject("https://fakestoreapi.com/products",
              fakeStoreCreateProductRequestDto, FakeStoreCreateProductResponseDto.class);
        Product product1 = new Product();

        product1.setId(response.getId());
        product1.setTitle(response.getTitle());
        product1.setDescription(response.getDescription());
        product1.setImageurl(response.getImage());
        product1.setPrice(response.getPrice());
        product1.setCategoryName(response.getCategory());

        return product1;
    }
}
