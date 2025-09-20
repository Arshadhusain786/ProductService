package com.example.product.services;

import com.example.product.dtos.products.CreateProductRequestDto;
import com.example.product.exceptions.ProductNotFoundException;
import com.example.product.models.Category;
import com.example.product.models.Product;
import com.example.product.repositories.CategoryRepository;
import com.example.product.repositories.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceDBImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @CachePut(value = "products", key = "#result.id") // cache newly created product
    @CacheEvict(value = "allProducts", allEntries = true) // clear product list cache
    public Product createProduct(Product product) {
        Category categoryToPutInProduct = getCategoryToPutInProduct(product);
        product.setCategory(categoryToPutInProduct);
        return productRepository.save(product);
    }

    @Override
    @Cacheable(value = "allProducts") // cache full product list
    public List<Product> getAllProducts() {
        System.out.println("Fetching all products from DB...");
        return productRepository.findAll();
    }

    @Override
    @CachePut(value = "products", key = "#productid") // update product in cache
    @CacheEvict(value = "allProducts", allEntries = true) // clear product list cache
    public Product partialUpdateProduct(Long productid, Product product) throws ProductNotFoundException {
        Optional<Product> productToUpdateOptional = productRepository.findById(productid);

        if (productToUpdateOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        Product productToUpdate = productToUpdateOptional.get();

        if (product.getDescription() != null) {
            productToUpdate.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            productToUpdate.setPrice(product.getPrice());
        }
        if (product.getTitle() != null) {
            productToUpdate.setTitle(product.getTitle());
        }
        if (product.getCategory() != null) {
            Category categoryToPutInProduct = getCategoryToPutInProduct(product);
            productToUpdate.setCategory(categoryToPutInProduct);
        }
        return productRepository.save(productToUpdate);
    }

    private Category getCategoryToPutInProduct(Product product) {
        String categoryName = product.getCategory().getName();
        Optional<Category> category = categoryRepository.findByName(categoryName);
        return category.orElseGet(() -> {
            Category toSaveCategory = new Category();
            toSaveCategory.setName(categoryName);
            return toSaveCategory;
        });
    }

    @Override
    @Cacheable(value = "products", key = "#productId") // cache single product
    public Product getSingleProduct(Long productId) throws ProductNotFoundException
    {
        System.out.println("Fetching product " + productId + " from DB...");

        Optional<Product> product = productRepository.findById(productId);

        if(product.isEmpty())
        {
            throw new ProductNotFoundException("Product not found with id"+productId);
        }
        else return product.orElse(null);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "products", key = "#id"),        // remove single product cache
            @CacheEvict(value = "allProducts", allEntries = true) // clear product list cache
    })
    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }


    @Override
    public Product replaceProduct(Long id, CreateProductRequestDto requestDto) {
        return null; // not yet implemented
    }
}
