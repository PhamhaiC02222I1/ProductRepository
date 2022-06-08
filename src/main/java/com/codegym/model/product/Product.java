package com.codegym.model.product;

import com.codegym.model.category.Category;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
@Component
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Please enter name again")
    @Size(min = 1,message = "name length from 3 to 300")
    private String name;
    @NotEmpty(message = "Enter price again")
//    @Pattern(regexp="\\d",message = "only number")
    private String price;
    @NotEmpty(message = "not null")
//    @Pattern(regexp="\\d",message = "only number")
    private String quantity;

    @NotEmpty(message = "not null")
    private String description;

    private String image;
    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, String price, String quantity, String description, String image, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Product.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        Product product = (Product) target;
//        String name = product.getName();
//
//        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
//        if (name.length() >= 100) {
//            errors.reject("name", "name.length");
//        }
//        if (name.contains("@") || name.contains(";") || name.contains(",") || name.contains(".")
//                || name.contains("=") || name.contains("+") ||
//                name.contains("-")) {
//            errors.reject("name", "name.contain");
//        }
//
//        String price = product.getPrice();
//        ValidationUtils.rejectIfEmpty(errors, "price", "price.empty");
//
//        String quantity = product.getQuantity();
//        ValidationUtils.rejectIfEmpty(errors, "quantity", "quantity.empty");
//        String description = product.getDescription();
//        ValidationUtils.rejectIfEmpty(errors, "description", "description.empty");
//        if (description.length() >= 800) {
//            errors.reject("description", "description.length");
//        }
//
//        String image = product.getImage();
//        ValidationUtils.rejectIfEmpty(errors, "image", "image.empty");
//
//    }
}
