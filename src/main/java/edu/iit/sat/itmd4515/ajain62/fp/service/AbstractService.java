/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * For All CRUD operations
 * @author Ankith Jain
 * @param <T>
 */
public abstract class AbstractService<T> {

    @PersistenceContext(unitName = "ajain62PU")
    private EntityManager em;

    private final Class<T> entityClass;

    /**
     *
     * @param entityClass
     */
    protected AbstractService(Class entityClass) {
        this.entityClass = entityClass;
    }

    /**
     *
     * @return entity manager
     */
    protected EntityManager getEntityManager() {
        return this.em;
    }

    /**
     * Create Method
     *
     * @param entity
     */
    public void create(T entity) {
        em.persist(entity);
    }

    /**
     * find method
     *
     * @param id
     * @return
     */
    public T find(Object id) {
        return em.find(entityClass, id);
    }

    /**
     * abstract findAll method
     *
     * @return
     */
    public abstract List<T> findAll();

    /**
     * update method
     *
     * @param entity
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     * delete method
     *
     * @param entity
     */
    public void delete(T entity) {
        em.remove(em.merge(entity));
    }

    /**
     * delete by id method.
     *
     * @param id
     */
    public void deleteById(Object id) {
        em.remove(em.getReference(entityClass, id));
    }

}
