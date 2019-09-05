package com.training.emelyanenko.repository;

import com.training.emelyanenko.domain.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Repository class that emulate data base and has some repository methods.
 */
@Repository
public class OrderRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public OrderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Saves order in database.
     *
     * @param order it's order to save.
     * @return saved order.
     */
    public Order save(Order order) {
        sessionFactory.getCurrentSession().save(order);
        return order;
    }

    public void updateOrder(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }


}
