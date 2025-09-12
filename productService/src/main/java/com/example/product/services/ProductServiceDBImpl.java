package com.example.product.services;

import com.example.product.ProductApplication;
import com.example.product.dtos.products.CreateProductRequestDto;
import com.example.product.exceptions.ProductNotFoundException;
import com.example.product.models.Category;
import com.example.product.models.Product;
import com.example.product.repositories.CategoryRepository;
import com.example.product.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService
{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public ProductServiceDBImpl(ProductRepository productRepository,CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product createProduct(Product product)
    {
        Category categoryToPutInProduct = getCategoryToPutInProduct(product);
        product.setCategory(categoryToPutInProduct);
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product partialUpdateProduct(Long productid,Product product) throws ProductNotFoundException {
        Optional<Product> productToUpdateOptional = productRepository.findById(productid);

        if(productToUpdateOptional.isEmpty())
        {
            throw  new ProductNotFoundException();
        }
        Product productToUpdate = productToUpdateOptional.get();
        if(product.getDescription()!=null)
        {
            productToUpdate.setDescription(product.getDescription());
        }
        if(product.getPrice()!=null)
        {
            productToUpdate.setPrice(product.getPrice());
        }
        if(product.getTitle()!=null)
        {
            productToUpdate.setTitle(product.getTitle());
        }
        if(product.getCategory()!=null)
        {
            Category categoryToPutInProduct = getCategoryToPutInProduct(product);
            productToUpdate.setCategory(categoryToPutInProduct);
        }
        return productRepository.save(productToUpdate);
    }

    private Category getCategoryToPutInProduct(Product product) {
        String categoryName = product.getCategory().getName();
        // object return in optional can never be null
        // to avoid null pointer exception..bcz category can be null also and we cant
        //change null value to string
        Optional<Category> category = categoryRepository.findByName(categoryName);
        Category categoryToPutInProduct = null;
        if(category.isEmpty())
        {
            Category toSaveCategory = new Category();
            toSaveCategory.setName(categoryName);
            categoryToPutInProduct=toSaveCategory;
        }
        else {
            categoryToPutInProduct = category.get();//getting existing category from db
        }
        return categoryToPutInProduct;
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
