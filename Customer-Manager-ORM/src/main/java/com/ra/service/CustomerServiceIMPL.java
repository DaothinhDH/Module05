package com.ra.service;

import com.ra.model.Customer;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
public class CustomerServiceIMPL implements ICustomerService{
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();  } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void save(Customer customer) {

    }
}
