package com.example.product.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel
{
  @Column(nullable = false, unique = true,name = "category_name")
  private String name;
  @Basic(fetch = FetchType.LAZY)
  private String description;
  @OneToMany(fetch = FetchType.EAGER)
  private List<Product> featuredProducts;
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "category",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
  //to avoid duplicate same table we used mappedBy
  // mapped by is only available in @OneToMany not@ManyToOne
  @Fetch(FetchMode.SELECT)
  private  List<Product> allProducts;
  @OneToOne
  private Subcategory subcategory;
}
