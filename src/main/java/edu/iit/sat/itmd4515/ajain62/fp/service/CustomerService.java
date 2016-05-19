/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.service;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Customer Service for Customer CRUD operations
 * @author Ankith Jain

 */
@Named
@Stateless
public class CustomerService extends AbstractService<Customer> {

    /**
     *
     */
    public CustomerService() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findAll() {
        return getEntityManager().createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    /**
     *
     * @param userName
     * @return
     */
    public Customer findByUsername(String userName) {

        Customer a = getEntityManager().createNamedQuery("Customer.findbyUsername", Customer.class).setParameter("userName", userName).getSingleResult();
        getEntityManager().flush();
        getEntityManager().refresh(a);
        return a;
    }

}
