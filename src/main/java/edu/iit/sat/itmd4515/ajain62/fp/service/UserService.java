/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.service;

import edu.iit.sat.itmd4515.ajain62.fp.domain.security.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 * User Service for User CRUD operations
 *
 * @author Ankith Jain
 *
 */
@Stateless
public class UserService extends AbstractService<User> {

    /**
     *
     */
    public UserService() {
        super(User.class);
    }

    @Override
    public List<User> findAll() {

        return getEntityManager().createNamedQuery("User.findAll", User.class).getResultList();

    }

}
