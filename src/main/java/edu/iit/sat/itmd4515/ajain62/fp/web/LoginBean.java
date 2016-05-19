/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Login Bean manages new Login operations
 *
 * @author Ankith Jain
 */
@Named
@RequestScoped
public class LoginBean extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(LoginBean.class.getName());
    @NotNull(message = "Please enter Username!")
    private String username;
    @NotNull(message = "Please enter Password!")
    @Size(min = 3, message = "Password must be at least 5 character in length")
    private String password;

    /**
     *
     */
    public LoginBean() {
    }

    @PostConstruct
    private void postConstruct() {
        super.postContruct();

    }

    /**
     *
     * @return
     */
    public boolean isAdmin() {
        return facesContext.getExternalContext().isUserInRole("admin");
    }

    /**
     *
     * @return
     */
    public boolean isCustomer() {
        return facesContext.getExternalContext().isUserInRole("customer");
    }

    /**
     *
     * @return
     */
    public boolean isEmployee() {
        return facesContext.getExternalContext().isUserInRole("employee");
    }

    /**
     *
     * @param path
     * @return
     */
    public String getPortalPathByRole(String path) {

        if (isAdmin()) {
            return "/admin" + path;
        } else if (isCustomer()) {
            return "/customerPortal" + path;
        } else if (isEmployee()) {
            return "/employeePortal" + path;
        } else {
            return path;
        }
    }

    /**
     *
     * @return
     */
    public String doLogin() {
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        try {
            req.login(username, password);
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            facesContext.addMessage(null, new FacesMessage("Bad Login", "Detail: You made a bad login!"));
            return "/login.xhtml";
        }
        return getPortalPathByRole("/welcome.xhtml");
    }

    /**
     *
     * @return
     */
    public String doLogout() {
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        try {
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            facesContext.addMessage(null, new FacesMessage("Bad logout", "Detail: You made a bad logout!"));
            return "/login.xhtml";
        }

        return "/login.xhtml";
    }

    /**
     *
     * @return
     */
    public String getRemoteUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
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

}
