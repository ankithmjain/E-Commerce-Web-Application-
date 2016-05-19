/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.domain;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Mapped superclass to generate id and last updated date.
 *
 * @author Ankith Jain
 */
@MappedSuperclass
public class SuperEntity {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * Last updated date
     */
    @Temporal(value = TemporalType.DATE)
    protected Date lastUpdatedDate;

    /**
     * Default Constructor
     */
    public SuperEntity() {
    }

    /**
     * Get the Value of id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Pre update the timestamp before persist
     */
    @PreUpdate
    @PrePersist
    public void setLastUpdatedDate() {
        lastUpdatedDate = GregorianCalendar.getInstance().getTime();
    }

    /**
     * Get the value of lastUpdated
     *
     * @return the value of lastUpdated
     */
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
