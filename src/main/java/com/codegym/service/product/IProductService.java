package com.codegym.service.product;

import com.codegym.model.category.Category;
import com.codegym.model.product.Product;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByCategory(Category category);

    Page<Product> findAll(Pageable pageable);
   Page<Product> findAllByOrderByPrice(Pageable pageable);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    Product saveProduct(Product product);
    List<Product> findAllByNameContaining(String name);
    List<Product> findAllByPriceIsGreaterThanEqual(String price);
    List<Product> findAllByCategoryId(Long id);


}
