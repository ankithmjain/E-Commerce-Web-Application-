/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.domain;

import edu.iit.sat.itmd4515.ajain62.fp.domain.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ankith Jain
 */
@Entity

@NamedQueries({
    @NamedQuery(name = "Customer.findbyLastName", query = "select n from Customer n where n.cust_last_name = :cust_last_name"),
    @NamedQuery(name = "Customer.findbyphone", query = "select n from Customer n where n.cust_ph = :cust_ph"),
    @NamedQuery(name = "Customer.findAll", query = "select b from Customer b"),
    @NamedQuery(name = "Customer.findbyUsername", query = "select c from Customer c where c.user.userName = :userName")
})
public class Customer extends SuperEntity implements Serializable {

    private String cust_first_name;
    private String cust_last_name;
    private String cust_email;
    private String cust_ph;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Address_ID")
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Orders> order = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Sale> sale = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     *
     */
    public Customer() {
    }

    /**
     * Customer
     *
     * @param cust_first_name
     * @param cust_last_name
     * @param cust_email
     * @param cust_ph
     */
    public Customer(String cust_first_name, String cust_last_name, String cust_email, String cust_ph) {
        this.cust_first_name = cust_first_name;
        this.cust_last_name = cust_last_name;
        this.cust_email = cust_email;
        this.cust_ph = cust_ph;
    }

    /**
     * Get the Value of cust_first_name
     *
     * @return cust_first_name
     */
    public String getCust_first_name() {
        return cust_first_name;
    }

    /**
     * Set the Value of cust_first_name
     *
     * @param cust_first_name
     */
    public void setCust_first_name(String cust_first_name) {
        this.cust_first_name = cust_first_name;
    }

    /**
     *
     * @return
     */
    public String getCust_last_name() {
        return cust_last_name;
    }

    /**
     * Set the Value of cust_last_name
     *
     * @param cust_last_name
     */
    public void setCust_last_name(String cust_last_name) {
        this.cust_last_name = cust_last_name;
    }

    /**
     * Get the Value of cust_email
     *
     * @return cust_email
     */
    public String getCust_email() {
        return cust_email;
    }

    /**
     * Set the Value of cust_email
     *
     * @param cust_email
     */
    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    /**
     * Get the Value of cust_ph
     *
     * @return cust_ph
     */
    public String getCust_ph() {
        return cust_ph;
    }

    /**
     * Set the Value of cust_ph
     *
     * @param cust_ph
     */
    public void setCust_ph(String cust_ph) {
        this.cust_ph = cust_ph;
    }

    /**
     * Get the Value of address
     *
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the Value of address
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Get the Value of order
     *
     * @return order
     */
    public List<Orders> getOrder() {
        return order;
    }

    /**
     * Set the Value of order
     *
     * @param order
     */
    public void setOrder(List<Orders> order) {
        this.order = order;
    }

    /**
     * Get the Value of sale
     *
     * @return sale
     */
    public List<Sale> getSale() {
        return sale;
    }

    /**
     * Set the Value of sale
     *
     * @param sale
     */
    public void setSale(List<Sale> sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", cust_first_name=" + cust_first_name + ", cust_last_name=" + cust_last_name + ", cust_email=" + cust_email + ", cust_ph=" + cust_ph + '}';
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

}
