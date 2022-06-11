package com.codegym.repository;

import com.codegym.model.category.Category;
import com.codegym.model.product.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends PagingAndSortingRepository<Category,Long> {


}
