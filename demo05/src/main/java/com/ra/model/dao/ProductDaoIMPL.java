package com.ra.model.dao;

import com.ra.model.entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDaoIMPL implements ProductDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        List<Product> products = new ArrayList<>();
        try {
            products = session.createQuery("from Product", Product.class).list();
        }catch (HibernateException exception){
           exception.printStackTrace();
        }finally {
            session.close();
        }
        return products;
    }

    @Override
    public boolean saveOfUpdate(Product product) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exception){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class,id);
        try {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        }catch (HibernateException exception){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public Product findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            Product product = session.get(Product.class,id);
            return product;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
