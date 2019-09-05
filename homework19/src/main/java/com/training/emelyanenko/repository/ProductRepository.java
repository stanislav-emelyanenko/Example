package com.training.emelyanenko.repository;

import com.training.emelyanenko.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Product> getAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product");
        return query.list();
    }

    public Product getById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product p where p.id = :id");
        query.setParameter("id", id);
        return (Product) query.list().get(0);
    }
}

