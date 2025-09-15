package com.example.product.repositories;

import com.example.product.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{

    // update is also done using save
    // if the product you try to save has an id
    // jpa will see if product with such id exist
    // if no-> it will insert
    //  if yes -> it will update
    @Override
    Product save(Product p);


    @Override
    void delete(Product entity);

    List<Product> findAll();


    Optional<Product> findById(Long id);

    List<Product>
    findAllByCategory_subcategory_surnameEquals(String subcategorySurname);

    List<Product>findByTitleContaining(String query);

    Page<Product> findAllByTitleContainingAndCategory_id(
            String title, Long categoryId, Pageable pageable);

}
