/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Product.findbyProdName", query = "select p from Product p where p.prod_name = :prod_name"),
    @NamedQuery(name = "Product.findAll", query = "select p from Product p"),})

public class Product extends SuperEntity implements Serializable {

    private String prod_name;
    private String prod_type;
    private Integer prod_price;

    @ManyToMany(mappedBy = "productM")
    private List<Orders> orders = new ArrayList<>();

    /**
     *
     */
    public Product() {
    }

    /**
     * Constructor
     *
     * @param prod_name
     * @param prod_type
     * @param prod_price
     */
    public Product(String prod_name, String prod_type, Integer prod_price) {
        this.prod_name = prod_name;
        this.prod_type = prod_type;
        this.prod_price = prod_price;
    }

    /**
     * Get the value of prod_name
     *
     * @return the value of prod_name
     */
    public String getProd_name() {
        return prod_name;
    }

    /**
     * Set the value of prod_name
     *
     * @param prod_name new value of prod_name
     */
    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    /**
     * Get the value of prod_type
     *
     * @return prod_type
     */
    public String getProd_type() {
        return prod_type;
    }

    /**
     * Set the value of prod_type
     *
     * @param prod_type
     */
    public void setProd_type(String prod_type) {
        this.prod_type = prod_type;
    }

    /**
     * Get the value of prod_price
     *
     * @return prod_price
     */
    public Integer getProd_price() {
        return prod_price;
    }

    /**
     * Set the value of prod_price
     *
     * @param prod_price
     */
    public void setProd_price(Integer prod_price) {
        this.prod_price = prod_price;
    }

    @Override
    public String toString() {
        return "Product{" + "prod_id=" + id + ", prod_name=" + prod_name + ", prod_type=" + prod_type + ", prod_price=" + prod_price + '}';
    }

    /**
     * Get the value of orders
     *
     * @return orders
     */
    public List<Orders> getOrders() {
        return orders;
    }

    /**
     * Set the value of orders
     *
     * @param orders
     */
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}
