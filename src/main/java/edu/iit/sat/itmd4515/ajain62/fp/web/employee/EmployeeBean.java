/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.web.employee;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Address;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Employee;
import edu.iit.sat.itmd4515.ajain62.fp.service.AddressService;
import edu.iit.sat.itmd4515.ajain62.fp.service.EmployeeService;
import edu.iit.sat.itmd4515.ajain62.fp.web.AbstractJSFBean;
import edu.iit.sat.itmd4515.ajain62.fp.web.LoginBean;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 * Employee Bean manages employee operations
 *
 * @author Ankith Jain
 *
 */
@Model
public class EmployeeBean extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(EmployeeBean.class.getName());

    private Employee employee;
    @EJB
    private EmployeeService employeeService;

    @EJB
    private AddressService addressService;

    @Inject
    private LoginBean loginBean;

    /**
     *
     */
    public EmployeeBean() {
        super();
    }

    @PostConstruct
    private void postConstruct() {
        super.postContruct();
        employee = new Employee();
        employee.setAddress(new Address());
        employee = employeeService.findByUsername(loginBean.getRemoteUser());
        LOG.info("Inside EmployeeBean PostConstruct Method " + employee.toString());
    }

    /**
     *
     * @return
     */
    public String executeUpdate() {

        LOG.info("EmployeeBean execute Update Method WITH ADDRESS " + employee.toString());
        employeeService.update(employee);
        addressService.update(employee.getAddress());
        LOG.info("UPDATED EMPLOYEE IN BEAN " + employee.toString());
        facesContext.addMessage(null, new FacesMessage("Successfully Updated the Changes"));
        return loginBean.getPortalPathByRole("/welcome.xhtml");
    }

    /**
     * Get the value of employee
     *
     * @return the value of employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set the value of employee
     *
     * @param employee new value of employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
