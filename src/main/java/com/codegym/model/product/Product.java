package com.codegym.model.product;

import com.codegym.model.category.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
//    @NotEmpty(message = "not null")
////    @Pattern(regexp="\\d",message = "only number")
//    private String quantity;
//
//    @NotEmpty(message = "not null")
//    private String description;
//
//    private String image;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Category category;


    public Product() {
    }

    public Product(Long id, String name, String price, String quantity, String description, String image, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
//        this.quantity = quantity;
//        this.description = description;
//        this.image = image;
        this.category = category;
    }

    public Product(String name, String price, String quantity, String description, String image) {
        this.name = name;
        this.price = price;
//        this.quantity = quantity;
//        this.description = description;
//        this.image = image;
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

//    public String getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
