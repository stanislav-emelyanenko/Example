package com.training.emelyanenko.controller;

import com.training.emelyanenko.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@Transactional
@RequestMapping("/basket")
public class BasketController {

    private OrderService orderService;

    @Autowired
    public BasketController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping
    public String showBasket(Principal principal, ModelMap model) {
        model.addAttribute("order", orderService.getOrder(principal.getName()));
        return "receipt";
    }
}
