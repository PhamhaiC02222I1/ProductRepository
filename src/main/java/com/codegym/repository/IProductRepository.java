package com.codegym.repository;

import com.codegym.model.category.Category;
import com.codegym.model.product.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product,Long> {
    Iterable<Product> findAllByCategory(Category category);
Page<Product> findAllByOrderByPrice(Pageable pageable);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

}
