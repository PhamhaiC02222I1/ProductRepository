package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.category.Category;
import com.codegym.model.product.Product;
import com.codegym.model.product.ProductForm;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/product")
@SessionAttributes("cart")

public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    public IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }


    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }


    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/product/error.404";
        }
        if (action.equals("show")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        cart.addProduct(productOptional.get());
        return "redirect:/product";
    }
    @GetMapping("/sub/{id}")
    public String subToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/product/error.404";
        }
        if (action.equals("show1")) {
            cart.subtractionProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
//        cart.addProduct(productOptional.get());
        return "redirect:/product";
    }


    @GetMapping("")
    public ModelAndView showList(@RequestParam("search") Optional<String> search, @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable) {
        Page<Product> products;
        if (search.isPresent()) {
            products = productService.findAllByNameContaining(search.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("product", products);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView saveProduct(@Validated @ModelAttribute(name = "product") ProductForm productForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         Product product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(), productForm.getQuantity(), productForm.getDescription(), fileName,
                productForm.getCategory());
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        } else {
            productService.save(product);
            modelAndView.addObject("message", "Created new product successfully !");
            return modelAndView;
        }
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-product/{id}")
    public ModelAndView updateBlog(@ModelAttribute ProductForm productForm, @PathVariable Long id) {
        MultipartFile multipartFile = productForm.getImage();
        Product product = productService.findById(id).get();
        String fileName = multipartFile.getOriginalFilename();
        if (fileName.equals("")) {
            fileName = product.getImage();
            product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(), productForm.getQuantity(), productForm.getDescription(), fileName, productForm.getCategory());
        } else {
            fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(), productForm.getQuantity(), productForm.getDescription(), fileName, productForm.getCategory());
        }
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Product updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:/product";
    }

    @GetMapping("/sortByPrice")
    public ModelAndView sortByPrice(Pageable pageable) {
        Page<Product> products = productService.findAllByOrderByPrice(pageable);
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("product", products);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        model.addAttribute("product", product.get());
        return "/product/view";
    }

}


