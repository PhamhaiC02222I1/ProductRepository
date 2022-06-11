package com.codegym.controller;

import com.codegym.model.category.Category;
import com.codegym.model.product.Product;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryWebserviceController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    public IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAllCategory(){
        List<Category> categories= (List<Category>) categoryService.findAll();
        if (categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id){
        Optional<Category> category=categoryService.findById(id);
        if (!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
        public ResponseEntity<Category> updateCategory(@PathVariable Long id,@RequestBody Category category){
        Optional<Category> category1=categoryService.findById(id);
        if (!category1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setId(category1.get().getId());
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.OK);


        }
       @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        Optional<Category> category=categoryService.findById(id);
        if (!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);

        return new ResponseEntity<>(category.get(),HttpStatus.OK);
       }
    @GetMapping("/viewProduct/{id}")
    public ResponseEntity<?> showProductByCategory(@PathVariable Long id){
      List<Product> products=productService.findAllByCategoryId(id);
      return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
