package com.codegym.minitest.service;

import com.codegym.minitest.model.Present;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class HibernatePresentService implements IPresentService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Present> findAll() {
        String queryStr = "SELECT c FROM Present AS c";
        TypedQuery<Present> query = entityManager.createQuery(queryStr, Present.class);
        return query.getResultList();
    }
    @Override
    public void save(Present present) {
        Transaction transaction = null;
        Present origin;
        if (present.getId() == 0) {
            origin = new Present();
        } else {
            origin = findById(present.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setName(present.getName());
            origin.setPrice(present.getPrice());
            origin.setCode(present.getCode());
            origin.setShip(present.getShip());
            origin.setImg(present.getImg());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public Present findById(int id) {
        String queryStr = "SELECT c FROM Present AS c WHERE c.id = :id";
        TypedQuery<Present> query = entityManager.createQuery(queryStr, Present.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    public void remove(int id) {
        Present present = findById(id);
        if (present != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(present);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
