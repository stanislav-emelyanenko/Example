package com.training.emelyanenko.service;

import com.training.emelyanenko.domain.Product;
import com.training.emelyanenko.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getPriceList() {
        return productRepository.getAll();
    }
}
