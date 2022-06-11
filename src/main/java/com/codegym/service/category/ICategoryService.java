package com.codegym.service.category;

import com.codegym.model.category.Category;
import com.codegym.model.product.Product;
import com.codegym.service.IGeneralService;
import org.springframework.stereotype.Service;

@Service
public interface ICategoryService extends IGeneralService<Category> {
    Category saveCategory(Category category);



}
