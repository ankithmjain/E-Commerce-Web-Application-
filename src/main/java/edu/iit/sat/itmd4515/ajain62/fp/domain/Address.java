/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Address.findbyAddressCity", query = "select b from Address b where b.addrs_city = :addrs_city"),
    @NamedQuery(name = "Address.findbyCountry", query = "select b from Address b where b.addrs_country = :addrs_country"),
    @NamedQuery(name = "Address.findAll", query = "select b from Address b"),})
public class Address extends SuperEntity implements Serializable {

    private String addrs_city;
    @OneToOne(mappedBy = "address")
    private Customer cust;

    private String addrs_state;

    private String addrs_country;

    private String addrs_zip;

    private String addrs_type;

    @OneToOne(mappedBy = "address")
    private Employee empadd;

    /**
     * Default Constructor
     */
    public Address() {
    }

    /**
     *
     * @param addrs_city
     */
    public Address(String addrs_city) {
        this.addrs_city = addrs_city;
    }

    /**
     * Constructor
     *
     * @param addrs_city
     * @param addrs_state
     * @param addrs_country
     * @param addrs_zip
     * @param addrs_type
     */
    public Address(String addrs_city, String addrs_state, String addrs_country, String addrs_zip, String addrs_type) {
        this.addrs_city = addrs_city;
        this.addrs_state = addrs_state;
        this.addrs_country = addrs_country;
        this.addrs_zip = addrs_zip;
        this.addrs_type = addrs_type;
    }

    /**
     * Get the Value of addrs_city
     *
     * @return addrs_city
     */
    public String getAddrs_city() {
        return addrs_city;
    }

    /**
     *
     * @param addrs_city
     */
    public void setAddrs_city(String addrs_city) {
        this.addrs_city = addrs_city;
    }

    /**
     * Get the Value of cust
     *
     * @return
     */
    public Customer getCust() {
        return cust;
    }

    /**
     * Set the Value of
     *
     * @param cust
     */
    public void setCust(Customer cust) {
        this.cust = cust;
    }

    /**
     * Get the Value of addrs_state
     *
     * @return
     */
    public String getAddrs_state() {
        return addrs_state;
    }

    /**
     *
     * @param addrs_state
     */
    public void setAddrs_state(String addrs_state) {
        this.addrs_state = addrs_state;
    }

    /**
     * Get the Value of addrs_country
     *
     * @return addrs_country
     */
    public String getAddrs_country() {
        return addrs_country;
    }

    /**
     * Set the Value of addrs_country
     *
     * @param addrs_country
     */
    public void setAddrs_country(String addrs_country) {
        this.addrs_country = addrs_country;
    }

    /**
     * Get the Value of addrs_zip
     *
     * @return
     */
    public String getAddrs_zip() {
        return addrs_zip;
    }

    /**
     * Set the Value of addrs_zip
     *
     * @param addrs_zip
     */
    public void setAddrs_zip(String addrs_zip) {
        this.addrs_zip = addrs_zip;
    }

    /**
     * Get the Value of addrs_type
     *
     * @return addrs_type
     */
    public String getAddrs_type() {
        return addrs_type;
    }

    /**
     * Set the Value of addrs_type
     *
     * @param addrs_type
     */
    public void setAddrs_type(String addrs_type) {
        this.addrs_type = addrs_type;
    }

    @Override
    public String toString() {
        return "Address{addrs_city=" + addrs_city + ", addrs_state=" + addrs_state + ", addrs_country=" + addrs_country + ", addrs_zip=" + addrs_zip + ", addrs_type=" + addrs_type + '}';
    }

    /**
     * Get the Value of empadd
     *
     * @return empadd
     */
    public Employee getEmpadd() {
        return empadd;
    }

    /**
     *
     * @param empadd
     */
    public void setEmpadd(Employee empadd) {
        this.empadd = empadd;
    }

    /**
     * Get the Value of addrsLastUpdate
     *
     * @return addrsLastUpdate
     */
}
