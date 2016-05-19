/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.domain.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Domain Security for User
 *
 * @author Ankith Jain
 */
@Entity
@Table(name = "tab_user")
@NamedQueries({
    @NamedQuery(name = "User.findbyLastName", query = "select n from User n where n.userName = :userName"),
    @NamedQuery(name = "User.findAll", query = "select b from User b")
})

public class User implements Serializable {

    @Id
    private String userName;

    private String password;

    @ManyToMany
    @JoinTable(name = "sec_user_group", joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME")
    )
    private List<Group> groups = new ArrayList<>();

    /**
     *
     * @param g
     */
    public void addGroup(Group g) {

        if (!this.groups.contains(g)) {
            this.groups.add(g);
        }

        if (!g.getUsers().contains(this)) {

            g.getUsers().add(this);
        }

    }

    /**
     *
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     */
    public User() {
    }

    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     *
     * @param groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @PrePersist
    @PreUpdate
    private void hashpassword() {
        String digestPassword = DigestUtils.md5Hex(this.password);
        this.password = digestPassword;
    }

}
