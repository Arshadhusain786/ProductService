package com.example.product.services;

import com.example.product.dtos.search.FilterDto;
import com.example.product.dtos.search.SortingCriteria;
import com.example.product.models.Product;
import com.example.product.repositories.ProductRepository;
import com.example.product.services.filteringService.FilterFactory;
import com.example.product.services.sortingService.SorterFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService
{
    private ProductRepository productRepository;
    public SearchService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }
    public Page<Product> search(String query,
                                List<FilterDto> filters,
                                SortingCriteria sortingCriteria,
                                int pageNumber,
                                int pageSize

                               )
    {
        List<Product>products = productRepository
                .findByTitleContaining(query);
        for(FilterDto filterDto : filters )
        {
            products = FilterFactory.getFilterFromKey(
                    filterDto.getKey()).apply(products,filterDto.getValues());
        }
        products = SorterFactory.getSortedByCriteria(sortingCriteria)
                .sort(products);

        int start = Math.min(pageSize * (pageNumber - 1), products.size());
        int end = Math.min(start + pageSize, products.size());

        List<Product> productsOnPage = products.subList(start, end);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new PageImpl<>(productsOnPage, pageable, products.size());

    }
    public Page<Product> simpleSearch(String query,
                                      Long categoryId,
                                      int pageNumber,
                                      int pageSize,
                                      String sortingAttribute)
    {

        Page<Product> products = productRepository
                .findAllByTitleContainingAndCategory_id(
                        query,
                        categoryId,
                        PageRequest.of(pageNumber,
                                pageSize,
                                Sort.by(sortingAttribute)));
       return products;
    }
}
