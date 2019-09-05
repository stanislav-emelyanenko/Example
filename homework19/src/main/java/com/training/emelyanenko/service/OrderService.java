package com.training.emelyanenko.service;

import com.training.emelyanenko.converter.OrderConverter;
import com.training.emelyanenko.domain.Order;
import com.training.emelyanenko.domain.Product;
import com.training.emelyanenko.domain.User;
import com.training.emelyanenko.dto.OrderDto;
import com.training.emelyanenko.exception.InvalidArgumentException;
import com.training.emelyanenko.exception.OrderNotFoundException;
import com.training.emelyanenko.repository.OrderRepository;
import com.training.emelyanenko.repository.ProductRepository;
import com.training.emelyanenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Order service class.
 */
@Service
public class OrderService {

    private OrderConverter orderConverter;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderService(OrderConverter orderConverter, ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.orderConverter = orderConverter;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates order and returns saved order.
     *
     * @param customer customer name.
     * @return created order.
     */
    public OrderDto getOrder(String customer) {
        return orderConverter.toDto(getCurrentOrder(customer));
    }

    public void addProductToOrder(String name, String[] selectedProducts) {
        if (name == null || selectedProducts == null) {
            throw new InvalidArgumentException("Arguments cant be null");
        }
        Order current = getCurrentOrder(name);
        List<Product> products = current.getProducts();
        int id = Integer.parseInt(selectedProducts[0]);
        Product product = productRepository.getById(id);
        products.add(product);
        current.setTotalPrice(calcTotalPrice(current));
        current.setProducts(products);
        orderRepository.updateOrder(current);
    }

    /**
     * Calculates total price of order.
     *
     * @param order order for calculation.
     * @return total price.
     */
    private double calcTotalPrice(Order order) {
        double totalPrice = 0.0;
        for (Product product : order.getProducts()) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    private Order getCurrentOrder(String name) {
        if (name == null) {
            throw new InvalidArgumentException("Name can't be null");
        }
        Optional<User> maybeUser = userRepository.getByName(name);
        if (!maybeUser.equals(Optional.empty()) && maybeUser.isPresent()) {
            User current = maybeUser.get();
            return current.getOrders().get(current.getOrders().size() - 1);
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }
}
