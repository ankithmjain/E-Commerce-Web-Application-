/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.web.customer;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Orders;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Product;
import edu.iit.sat.itmd4515.ajain62.fp.service.CustomerService;
import edu.iit.sat.itmd4515.ajain62.fp.service.OrderService;
import edu.iit.sat.itmd4515.ajain62.fp.service.ProductService;
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
 * Order Bean manages order operations
 *
 * @author Ankith Jain
 */
@Model
public class OrderBean extends AbstractJSFBean {

    @EJB
    private ProductService productService;

    @EJB
    private OrderService orderService;

    private static final Logger LOG = Logger.getLogger(OrderBean.class.getName());

    private Orders order;
    private List<Orders> orders;
    private Product product;
    private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
    private List<Product> products;

    @EJB
    private CustomerService customerService;

    @Inject
    private LoginBean loginBean;

    /**
     *
     */
    public OrderBean() {
        super();
    }

    private void refreshOrders() {
        orders = orderService.findOrdersByCustomerName(loginBean.getRemoteUser());
    }

    @PostConstruct
    private void postConstruct() {

        super.postContruct();
        order = new Orders();
        product = new Product();

        products = productService.findAll();

        refreshOrders();
        LOG.info("Orders FULL List" + orders.toString());
    }

    /**
     *
     * @param order
     * @return
     */
    public String doUpdate(Orders order) {

        this.order = order;
        LOG.info("The Order to update is " + order.toString());

        refreshOrders();
        return "/customerPortal/order";

    }

    /**
     *
     * @return
     */
    public String executeSave() {
        if (this.order.getId() != null) {

            LOG.info("Executing update Order Bean on " + this.order.toString());

            orderService.update(order);
            refreshOrders();
            facesContext.addMessage(null, new FacesMessage("Successfully UPDATED Order having Order ID " + order.getId()));

            return loginBean.getPortalPathByRole("/welcome.xhtml");
        } else {

            LOG.info("Creating Order in Order Bean ");

            List<Product> checkedItems = new ArrayList<Product>();
            int size = 0;
            for (Boolean val : checked.values()) {
                if (val) {
                    size++;
                }
            }

            if (size < 1) {
                facesContext.addMessage(null, new FacesMessage("Select at least one Product From Below", "Detail: You made a more selection!"));
                checked.clear();
                return "/customerPortal/order";
            }

            LOG.info("Checked ITEMS product Id " + checked.toString());
            for (Product p1 : products) {
                if (checked.get(p1.getId())) {
                    checkedItems.add(p1);
                    LOG.info("ORDER product Id in FOR LOOP" + p1.toString());

                }
            }

            checked.clear();

            orderService.create(order, customerService.findByUsername(loginBean.getRemoteUser()), checkedItems);
            LOG.info("ORDER LATEST product Id " + order.toString());

            refreshOrders();

            facesContext.addMessage(null, new FacesMessage("Your Order is successful with the Order ID " + order.getId() + " and Total Order Amount is " + order.getOrdr_qty()));

            return loginBean.getPortalPathByRole("/welcome.xhtml");

        }
    }

    /**
     *
     * @param order
     * @return
     */
    public String doDelete(Orders order) {
        try {
            LOG.info("The order to delete is " + order.toString());
            orderService.delete(order);
            refreshOrders();
            facesContext.addMessage(null, new FacesMessage("Successfully Deleted Order "));
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage("Cannot Delete Order as SALE is Generated"));
        }

        return loginBean.getPortalPathByRole("/welcome.xhtml");

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
    public Product getProduct() {
        return product;
    }

    /**
     *
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     *
     * @return
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     *
     * @param products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
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
