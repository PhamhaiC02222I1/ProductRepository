package com.codegym.controller;

import com.codegym.model.product.Product;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class Product11Cpntroller {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAllProduct(){
        List<Product> products= (List<Product>) productService.findAll();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        Optional<Product> product=productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.saveProduct(product),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id ,@RequestBody Product product){
        Optional<Product> productOptional=productService.findById(id);
        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(productOptional.get().getId());
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        Optional<Product> product=productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(product.get(),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findbyname")
    public ResponseEntity<List<Product>> findProductById(@RequestParam String name){
       List<Product> products=productService.findAllByNameContaining(name);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
