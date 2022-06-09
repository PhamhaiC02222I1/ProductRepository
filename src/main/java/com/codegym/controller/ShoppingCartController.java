package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
public class ShoppingCartController {

    @ModelAttribute("cart")
    public Cart setupCart(){
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart (@SessionAttribute("cart") Cart cart){
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("cart",cart);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String deleteCart(@PathVariable Long id, @SessionAttribute("cart") Cart cart){
        Map.Entry<Product, Integer> productIntegerEntry=cart.selectProductInCart(id);
//
        cart.deleteProductInCar(productIntegerEntry.getKey());
        return "redirect:/shopping-cart";

    }
}