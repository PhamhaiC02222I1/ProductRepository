package com.codegym.model.product;

import com.codegym.model.category.Category;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ProductForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

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
    private MultipartFile image;
    private Category category;

    public ProductForm() {
    }

    public ProductForm(Long id, String name, String price, String quantity, String description, MultipartFile image, Category category) {
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
