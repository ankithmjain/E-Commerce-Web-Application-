/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.service;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * Employee Service for Employee CRUD operations
 * @author Ankith Jain
 */
@Stateless
@Named
public class EmployeeService extends AbstractService<Employee> {

    /**
     *
     */
    public EmployeeService() {
        super(Employee.class);
    }

    @Override
    public List<Employee> findAll() {
        return getEntityManager().createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    /**
     *
     * @param userName
     * @return
     */
    public Employee findByUsername(String userName) {

        return getEntityManager().createNamedQuery("Employee.findbyUsername", Employee.class).setParameter("userName", userName).getSingleResult();

    }

}
