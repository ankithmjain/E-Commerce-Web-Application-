/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.service;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Customer;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Orders;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Product;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Order Service for ORDER CRUD operations
 * @author Ankith Jain

 */
@Named
@Stateless
public class OrderService extends AbstractService<Orders> {

    private static final Logger LOG = Logger.getLogger(OrderService.class.getName());

    @Inject
    private CustomerService custService;

    @Inject
    private ProductService productService;

    /**
     *
     */
    public OrderService() {
        super(Orders.class);
    }

    @Override
    public List<Orders> findAll() {

        return getEntityManager().createNamedQuery("Orders.findAll", Orders.class).getResultList();
    }

    /**
     *
     * @param userName
     * @return
     */
    public List<Orders> findOrdersByCustomerName(String userName) {
        LOG.info("Calling findByCustomerName for User" + userName);

        Customer cust = custService.findByUsername(userName);

        LOG.info("Orders By Customer -" + cust.getCust_first_name() + cust.getOrder().toString() + cust.toString());

        return cust.getOrder();

    }

    @Override
    public void update(Orders order) {

        Orders currentorder = getEntityManager().getReference(
                Orders.class,
                order.getId()
        );

        order.setCustomer(currentorder.getCustomer());
        order.setProductM(currentorder.getProductM());
        LOG.info("UPDATE OF ORDER Order Service on " + order.toString());
        LOG.info("UPDATE OF ORDER with EAGER FETCH OF PRODUCTS Order Service on " + order.getProductM().toString());

        super.update(order);

    }

    /**
     *
     * @param order
     * @param customer
     * @param product
     */
    public void create(Orders order, Customer customer, List<Product> product) {

        customer = getEntityManager().getReference(
                Customer.class,
                customer.getId()
        );
        LOG.info("MAY LIST REVISED CUSTOMER on ORDER SERVICE for new order " + customer.toString());
        LOG.info("MAY LiST NEW PRODUCT for order ORDER SERVICE is " + product.toString());
        order.setCustomer(customer);

        for (Product p1 : product) {
            order.addProducts(p1);
        }

        List<Product> patS = productService.findAll();
        LOG.info("Products at Order SERVICE" + patS.toString());
        super.create(order);

        LOG.info("Products at after Order CREATION" + patS.toString());
        LOG.info("Creating Order Service on " + order.toString());

    }

}
