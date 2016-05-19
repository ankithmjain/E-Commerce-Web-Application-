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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Orders.findbyStatus", query = "select n from Orders n where n.ordr_status = :ordr_status"),
    @NamedQuery(name = "Orders.findAll", query = "select b from Orders b"),})
public class Orders extends SuperEntity implements Serializable {

    private String ordr_desc;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ordersProduct", joinColumns = @JoinColumn(name = "Order_id"),
            inverseJoinColumns = @JoinColumn(name = "Prod_id"))
    private List<Product> productM = new ArrayList<>();

    @OneToOne(mappedBy = "orders")
    private Sale sale;
    private String ordr_status;

    private Integer ordr_qty;

    /**
     *
     * @param ordr_desc
     * @param ordr_status
     * @param ordr_qty
     */
    public Orders(String ordr_desc, String ordr_status, Integer ordr_qty) {
        this.ordr_desc = ordr_desc;
        this.ordr_status = ordr_status;
        this.ordr_qty = ordr_qty;
    }

    /**
     *
     * @param product
     */
    public void addProducts(Product product) {
        if (!productM.contains(product)) {
            productM.add(product);
        }
    }

    /**
     * Default Constructor
     */
    public Orders() {
    }

    /**
     * Get the value of order_id
     *
     * @return the value of order_id
     */
    /**
     * Get the value of ordr_desc
     *
     * @return ordr_desc
     */
    public String getOrdr_desc() {
        return ordr_desc;
    }

    /**
     * Set the value of ordr_desc
     *
     * @param ordr_desc
     */
    public void setOrdr_desc(String ordr_desc) {
        this.ordr_desc = ordr_desc;
    }

    /**
     * Get the value of customer
     *
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + id + ", ordr_desc=" + ordr_desc + ", customer=" + customer + '}';
    }

    /**
     * Get the value of productM
     *
     * @return productM
     */
    public List<Product> getProductM() {
        return productM;
    }

    /**
     * Set the value of productM
     *
     * @param productM
     */
    public void setProductM(List<Product> productM) {
        this.productM = productM;
    }

    /**
     * Get the value of sale
     *
     * @return sale
     */
    public Sale getSale() {
        return sale;
    }

    /**
     * Set the value of sale
     *
     * @param sale
     */
    public void setSale(Sale sale) {
        this.sale = sale;
    }

    /**
     * Get the value of ordr_status
     *
     * @return ordr_status
     */
    public String getOrdr_status() {
        return ordr_status;
    }

    /**
     * Set the value of ordr_status
     *
     * @param ordr_status
     */
    public void setOrdr_status(String ordr_status) {
        this.ordr_status = ordr_status;
    }

    /**
     * Get the value of ordr_qty
     *
     * @return ordr_qty
     */
    public Integer getOrdr_qty() {
        return ordr_qty;
    }

    /**
     * Set the value of ordr_qty
     *
     * @param ordr_qty
     */
    public void setOrdr_qty(Integer ordr_qty) {
        this.ordr_qty = ordr_qty;
    }

}
