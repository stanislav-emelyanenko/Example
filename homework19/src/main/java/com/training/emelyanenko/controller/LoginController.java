package com.training.emelyanenko.controller;

import com.training.emelyanenko.service.OrderService;
import com.training.emelyanenko.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@Transactional
public class LoginController {

    private OrderService orderService;
    private ProductService productService;

    @Autowired
    public LoginController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    /**
     * Аnalogue of welcome page in servlet app.
     *
     * @return login view.
     */
    @GetMapping("/")
    public String goToLoginPage() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(Principal principal, ModelAndView model) {
        if (principal != null) {
            model.addObject("products", productService.getPriceList());
            model.addObject("order", orderService.getOrder(principal.getName()));
            return "product";
        } else {
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }
}
