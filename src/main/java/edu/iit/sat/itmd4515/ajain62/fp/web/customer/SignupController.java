/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.web.customer;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Address;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Customer;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Employee;
import edu.iit.sat.itmd4515.ajain62.fp.domain.security.Group;
import edu.iit.sat.itmd4515.ajain62.fp.domain.security.User;
import edu.iit.sat.itmd4515.ajain62.fp.service.AddressService;
import edu.iit.sat.itmd4515.ajain62.fp.service.CustomerService;
import edu.iit.sat.itmd4515.ajain62.fp.service.EmployeeService;
import edu.iit.sat.itmd4515.ajain62.fp.service.GroupService;
import edu.iit.sat.itmd4515.ajain62.fp.service.UserService;
import edu.iit.sat.itmd4515.ajain62.fp.web.AbstractJSFBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * SignupController, employee and customer can login
 *
 * @author Ankith Jain
 */
@Named
@RequestScoped
public class SignupController extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(SignupController.class.getName());

    @EJB
    private EmployeeService employeeService;

    @EJB
    private GroupService groupService;

    @EJB
    private AddressService addressService;

    private Customer customer;

    private Employee employee;

    private Address address;

    private User user;

    private Group custGroup;

    @EJB
    private CustomerService custService;

    @EJB
    private UserService userService;

    /**
     *
     */
    public SignupController() {
        super();
    }

    @PostConstruct
    private void postConstruct() {
        employee = new Employee();
        customer = new Customer();
        address = new Address();
        user = new User();
        custGroup = new Group();

        super.postContruct();
    }

    /**
     *
     * @return
     */
    public String createcustomer() {
        try {
            LOG.info("CREATE CUSTOMER CONTROLLER");
            custGroup = new Group("Customers", "Group of Customer");

            user.addGroup(custGroup);
            userService.create(user);
            addressService.create(address);

            customer.setAddress(address);
            customer.setUser(user);

            custService.create(customer);
            facesContext.addMessage(null, new FacesMessage("Successfully Created Customer, Now Login Again"));

        } catch (Exception e) {
            LOG.log(Level.INFO, "Creating a new user has encountered Error", e);
            facesContext.addMessage(null, new FacesMessage("Creating a new user has encountered Error please use different username" + e));
        }
        return "login.xhtml";
    }

    /**
     *
     * @return
     */
    public String createemployee() {

        LOG.info("CREATE EMPLOYEE CONTROLLER");
        custGroup = new Group("Employees", "Group of Employee");

        user.addGroup(custGroup);
        userService.create(user);
        addressService.create(address);
        employee.setAddress(address);
        employee.setUser(user);

        employeeService.create(employee);
        facesContext.addMessage(null, new FacesMessage("Successfully Created Employee, Now Login Again"));

        return "login.xhtml";
    }

    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     *
     * @return
     */
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public Group getCustGroup() {
        return custGroup;
    }

    /**
     *
     * @param custGroup
     */
    public void setCustGroup(Group custGroup) {
        this.custGroup = custGroup;
    }

    /**
     *
     * @return
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     *
     * @param employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
