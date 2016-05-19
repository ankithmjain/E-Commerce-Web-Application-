/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.service;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Address;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *Address Service for Address CRUD operations
 * @author Ankith Jain
 */
@Named
@Stateless
public class AddressService extends AbstractService<Address> {

    /**
     *
     */
    public AddressService() {
        super(Address.class);
    }

    @Override
    public List<Address> findAll() {
        return getEntityManager().createNamedQuery("Address.findAll", Address.class).getResultList();

    }

}
