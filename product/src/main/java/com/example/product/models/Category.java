package com.example.product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel
{
  private  Long id;
  private String name;
  private String description;
  @OneToMany
  private List<Product> featuredProducts;
  @OneToMany(mappedBy = "category")
  //to avoid duplicate same table we used mappedBy
  // mapped by is only available in @OneToMany not@ManyToOne
  private  List<Product> allProducts;
}
