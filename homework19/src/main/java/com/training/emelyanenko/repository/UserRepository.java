package com.training.emelyanenko.repository;

import com.training.emelyanenko.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Saves user in "data base" (list of orders).
     *
     * @param user it's order to save.
     * @return saved user.
     */
    public User save(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    public Optional<User> getByName(String customer) {
        Optional<User> maybeUser = Optional.empty();
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.login = :login");
        query.setParameter("login", customer);
        List user = query.list();
        if (user == null) {
            return maybeUser;
        } else {
            return Optional.of((User) user.get(0));
        }
    }
}

