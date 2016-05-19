/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.web.employee;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Orders;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Sale;
import edu.iit.sat.itmd4515.ajain62.fp.service.EmployeeService;
import edu.iit.sat.itmd4515.ajain62.fp.service.OrderService;
import edu.iit.sat.itmd4515.ajain62.fp.service.SaleService;
import edu.iit.sat.itmd4515.ajain62.fp.web.AbstractJSFBean;
import edu.iit.sat.itmd4515.ajain62.fp.web.LoginBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * Sale Bean manages sale operations
 *
 * @author Ankith Jain
 *
 */
@Model
public class SaleBean extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(SaleBean.class.getName());

    private Orders order;

    private Sale sale;

    private List<Orders> orders;

    @EJB
    SaleService saleService;

    @EJB
    private OrderService orderService;

    @EJB
    EmployeeService employeeService;

    @Inject
    private LoginBean loginBean;

    private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();

    /**
     *
     */
    public SaleBean() {
        super();
    }

    @PostConstruct
    private void postConstruct() {
        super.postContruct();
        order = new Orders();
        sale = new Sale();
        orders = orderService.findAll();
        checked.clear();

    }

    /**
     *
     * @return
     */
    public String executeSave() {
        try {

            List<Orders> checkedItems = new ArrayList<Orders>();
            int size = 0;
            for (Boolean val : checked.values()) {
                if (val) {
                    size++;
                }
            }

            if (size != 1) {
                facesContext.addMessage(null, new FacesMessage("Select Check Box / Select Single Check Box Only", "Detail: You made a more selection!"));
                checked.clear();
                return "/employeePortal/createSale";
            }

            for (Orders p1 : orders) {
                if (checked.get(p1.getId())) {
                    checkedItems.add(p1);
                    p1.setOrdr_status(this.order.getOrdr_status());

                    orderService.update(p1);

                }
            }
            checked.clear();

            saleService.create(sale, employeeService.findByUsername(loginBean.getRemoteUser()), checkedItems.get(0));
            facesContext.addMessage(null, new FacesMessage("Successfully PROCESSED the ORDER to Sale with ID " + sale.getId()));

            LOG.info("SALE CREATED WITH BEAN " + sale.toString());

        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage("ITS PROCESSED TO SALE ALREADY, Select OPEN Orders to Process for SALE"));

        }

        return "/employeePortal/createSale";

    }

    /**
     *
     * @return
     */
    public Orders getOrder() {
        return order;
    }

    /**
     *
     * @param order
     */
    public void setOrder(Orders order) {
        this.order = order;
    }

    /**
     *
     * @return
     */
    public Sale getSale() {
        return sale;
    }

    /**
     *
     * @param sale
     */
    public void setSale(Sale sale) {
        this.sale = sale;
    }

    /**
     *
     * @return
     */
    public List<Orders> getOrders() {
        return orders;
    }

    /**
     *
     * @param orders
     */
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    /**
     *
     * @return
     */
    public Map<Long, Boolean> getChecked() {
        return checked;
    }

    /**
     *
     * @param checked
     */
    public void setChecked(Map<Long, Boolean> checked) {
        this.checked = checked;
    }

}
