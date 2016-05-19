/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.domain;

import edu.iit.sat.itmd4515.ajain62.fp.domain.security.User;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @NamedQuery(name = "Employee.findbyLastName", query = "select n from Employee n where n.emp_last_name = :emp_last_name"),
    @NamedQuery(name = "Employee.findbyphone", query = "select n from Employee n where n.emp_ph = :emp_ph"),
    @NamedQuery(name = "Employee.findAll", query = "select b from Employee b"),
    @NamedQuery(name = "Employee.findbyUsername", query = "select e from Employee e where e.user.userName = :userName")
})
public class Employee extends SuperEntity implements Serializable {

    private String emp_first_name;

    private String emp_last_name;

    private String emp_email;

    private String emp_ph;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToOne(mappedBy = "empsale")
    private Sale e_sale;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     * Default Constructor
     */
    public Employee() {
    }

    /**
     * Constructor
     *
     * @param emp_first_name
     * @param emp_last_name
     * @param emp_email
     * @param emp_ph
     */
    public Employee(String emp_first_name, String emp_last_name, String emp_email, String emp_ph) {
        this.emp_first_name = emp_first_name;
        this.emp_last_name = emp_last_name;
        this.emp_email = emp_email;
        this.emp_ph = emp_ph;
    }

    /**
     * Get the Value of emp_first_name
     *
     * @return emp_first_name
     */
    public String getEmp_first_name() {
        return emp_first_name;
    }

    /**
     * Set the value of emp_first_name
     *
     * @param emp_first_name
     */
    public void setEmp_first_name(String emp_first_name) {
        this.emp_first_name = emp_first_name;
    }

    /**
     * Get the Value of emp_last_name
     *
     * @return emp_last_name
     */
    public String getEmp_last_name() {
        return emp_last_name;
    }

    /**
     * Set the value of emp_last_name
     *
     * @param emp_last_name
     */
    public void setEmp_last_name(String emp_last_name) {
        this.emp_last_name = emp_last_name;
    }

    /**
     * Get the Value of emp_email
     *
     * @return emp_email
     */
    public String getEmp_email() {
        return emp_email;
    }

    /**
     * Set the value of emp_email
     *
     * @param emp_email
     */
    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    /**
     * Get the Value of emp_ph
     *
     * @return emp_ph
     */
    public String getEmp_ph() {
        return emp_ph;
    }

    /**
     * Set the value of emp_ph
     *
     * @param emp_ph
     */
    public void setEmp_ph(String emp_ph) {
        this.emp_ph = emp_ph;
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
     * Set the value of address
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Get the Value of e_sale
     *
     * @return e_sale
     */
    public Sale getE_sale() {
        return e_sale;
    }

    /**
     * Set the value of e_sale
     *
     * @param e_sale
     */
    public void setE_sale(Sale e_sale) {
        this.e_sale = e_sale;
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

    @Override
    public String toString() {
        return "Employee{" + "emp_first_name=" + emp_first_name + ", emp_last_name=" + emp_last_name + ", emp_email=" + emp_email + ", emp_ph=" + emp_ph + ", address=" + address + ", e_sale=" + e_sale + ", user=" + user + '}';
    }

}
