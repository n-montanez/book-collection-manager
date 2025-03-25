package com.globant.model.dao;

import jakarta.persistence.*;

import java.util.List;

public class GenericDaoImpl<T> implements GenericDAO<T> {
    private EntityManager entityManager;
    private final Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPU")) {
            this.entityManager = emf.createEntityManager();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(T object) {
        EntityTransaction saveTransaction = entityManager.getTransaction();
        saveTransaction.begin();
        entityManager.persist(object);
        saveTransaction.commit();
    }

    @Override
    public void update(T object) {
        EntityTransaction updateTransaction = entityManager.getTransaction();
        updateTransaction.begin();
        entityManager.merge(object);
        updateTransaction.commit();
    }

    @Override
    public void delete(T object) {
        EntityTransaction deleteTransaction = entityManager.getTransaction();
        deleteTransaction.begin();
        entityManager.remove(object);
        deleteTransaction.commit();
    }

    @Override
    public T findById(Long id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager
                .createQuery("FROM " + entityClass.getSimpleName(), entityClass)
                .getResultList();
    }
}
