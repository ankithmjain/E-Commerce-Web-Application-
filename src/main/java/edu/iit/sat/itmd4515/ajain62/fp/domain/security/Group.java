/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.domain.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * Domain Security for Group
 *
 * @author Ankith Jain
 *
 */
@Entity
@Table(name = "sec_group")
@NamedQueries({
    @NamedQuery(name = "Group.findbyGroupName", query = "select n from Group n where n.groupName = :groupName"),
    @NamedQuery(name = "Group.findAll", query = "select b from Group b")
})
public class Group implements Serializable {

    @Id
    private String groupName;

    private String groupDescription;

    @ManyToMany(mappedBy = "groups", cascade = CascadeType.PERSIST)
    private List<User> users = new ArrayList<>();

    /**
     *
     * @param u
     */
    public void addUser(User u) {

        if (!this.users.contains(u)) {
            this.users.add(u);

        }
        if (!u.getGroups().contains(this)) {

            u.getGroups().add(this);

        }
    }

    /**
     *
     */
    public Group() {
    }

    /**
     *
     * @param groupName
     * @param groupDescription
     */
    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    /**
     * Get the value of groupName
     *
     * @return the value of groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set the value of groupName
     *
     * @param groupName new value of groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     *
     * @return
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     *
     * @param groupDescription
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     *
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
