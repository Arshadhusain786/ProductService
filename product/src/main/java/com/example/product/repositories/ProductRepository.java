package com.example.product.repositories;

import com.example.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
