/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @NamedQuery(name = "Sale.findAll", query = "select p from Sale p"),
    @NamedQuery(name = "Sale.findbySalesNote", query = "select n from Sale n where n.sales_note = :sales_note")
})
public class Sale extends SuperEntity implements Serializable {

    private String sales_note;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_ID", unique = true)

    private Orders orders;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Employee_ID")
    private Employee empsale;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Customer_ID")
    private Customer customer;

    /**
     * Constructor
     *
     * @param sales_note
     */
    public Sale(String sales_note) {
        this.sales_note = sales_note;
    }

    /**
     * Default Constructor
     */
    public Sale() {
    }

    /**
     * Get the value of sales_note
     *
     * @return sales_note
     */
    public String getSales_note() {
        return sales_note;
    }

    /**
     * Set the value of sales_note
     *
     * @param sales_note
     */
    public void setSales_note(String sales_note) {
        this.sales_note = sales_note;
    }

    /**
     * Get the value of orders
     *
     * @return orders
     */
    public Orders getOrders() {
        return orders;
    }

    /**
     * Set the value of orders
     *
     * @param orders
     */
    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    /**
     * Get the value of empsale
     *
     * @return empsale
     */
    public Employee getEmpsale() {
        return empsale;
    }

    /**
     * Set the value of empsale
     *
     * @param empsale
     */
    public void setEmpsale(Employee empsale) {
        this.empsale = empsale;
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
        return "Sale{" + "sales_id=" + id + ", sales_date=" + lastUpdatedDate + ", sales_note=" + sales_note + ", orders=" + orders + ", empsale=" + empsale + ", customer=" + customer + '}';
    }

}
