package com.example.product.repositories;

import com.example.product.models.Category;
import com.example.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long>
{
    Optional<Category> findByName(String name);
    Category save(Category category);
}
