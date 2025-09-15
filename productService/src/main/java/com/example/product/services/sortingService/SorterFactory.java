package com.example.product.services.sortingService;

import com.example.product.dtos.search.SortingCriteria;

public class SorterFactory
{
    public static Sorter getSortedByCriteria(SortingCriteria sortingCriteria)
    {
        return switch (sortingCriteria)
        {
            case RELEVANCE -> null;
            case POPULARITY -> null;
            case PRICE_HIGH_TO_LOW -> new PriceHighToLowSorter();
            case PRICE_LOW_TO_HIGH -> new PriceLowToHighSorter();
            case RATING_HIGH_TO_lOW -> null;
            case RATING_LOW_TO_HIGH -> null;
            case null , default -> null;
        };
    }
}
