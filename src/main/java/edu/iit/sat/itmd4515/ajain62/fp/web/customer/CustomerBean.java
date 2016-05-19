/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.web.customer;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Address;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Customer;
import edu.iit.sat.itmd4515.ajain62.fp.service.AddressService;
import edu.iit.sat.itmd4515.ajain62.fp.service.CustomerService;
import edu.iit.sat.itmd4515.ajain62.fp.web.AbstractJSFBean;
import edu.iit.sat.itmd4515.ajain62.fp.web.LoginBean;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 * Customer Bean manages customer operations
 *
 * @author Ankith Jain
 */
@Model
public class CustomerBean extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(CustomerBean.class.getName());

    @EJB
    CustomerService customerService;

    @EJB
    private AddressService addressService;

    @Inject
    private LoginBean loginBean;

    private Customer customer;

    /**
     *
     */
    public CustomerBean() {
        super();
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("POST CONSTRUCT BEAN METHOD IN CUSTOMER BEAN");
        super.postContruct();

        customer = new Customer();
        customer.setAddress(new Address());
        customer = customerService.findByUsername(loginBean.getRemoteUser());
        LOG.info("INSIDE CUSTOMER Bean PostConstruct Method " + customer.toString());

    }

    /**
     *
     * @return
     */
    public String executeUpdate() {

        LOG.info("NEW EXECUTE UPDATE CUSTOMER Bean Method " + customer.toString());
        customerService.update(customer);
        addressService.update(customer.getAddress());
        LOG.info("UPDATED CUSTOMER IN BEAN " + customer.toString());

        facesContext.addMessage(null, new FacesMessage("Successfully Updated the Changes"));
        return loginBean.getPortalPathByRole("/welcome.xhtml");
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

}
