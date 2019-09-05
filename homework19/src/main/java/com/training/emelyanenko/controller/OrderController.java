package com.training.emelyanenko.controller;

import com.training.emelyanenko.service.OrderService;
import com.training.emelyanenko.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@Transactional
@RequestMapping("/product")
public class OrderController {

    private OrderService orderService;
    private ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @RequestMapping
    public ModelAndView makeOrder(ModelAndView model, Principal principal, @RequestParam(value = "selected", required = false) String[] selected) {
        model.setViewName("product");
        if (selected != null) {
            orderService.addProductToOrder(principal.getName(), selected);
        }
        model.addObject("products", productService.getPriceList());
        model.addObject("order", orderService.getOrder(principal.getName()));
        return model;
    }
}