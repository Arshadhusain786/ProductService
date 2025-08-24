package com.example.product.repositories;

import com.example.product.models.Category;
import com.example.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>
{
}
