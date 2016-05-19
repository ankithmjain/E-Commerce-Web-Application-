/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.service;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Employee;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Orders;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Sale;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Sale Service for Sale CRUD operations
 *
 * @author Ankith Jain
 *
 */
@Named
@Stateless
public class SaleService extends AbstractService<Sale> {

    private static final Logger LOG = Logger.getLogger(SaleService.class.getName());

    /**
     *
     */
    public SaleService() {
        super(Sale.class);
    }

    @Override
    public List<Sale> findAll() {
        return getEntityManager().createNamedQuery("Sale.findAll", Sale.class).getResultList();
    }

    /**
     *
     * @param sale
     * @param employee
     * @param orders
     */
    public void create(Sale sale, Employee employee, Orders orders) {

        employee = getEntityManager().getReference(Employee.class, employee.getId());
        Orders currentorder = getEntityManager().getReference(
                Orders.class,
                orders.getId()
        );

        sale.setEmpsale(employee);
        sale.setOrders(orders);
        sale.setCustomer(currentorder.getCustomer());

        super.create(sale);
        LOG.info("NOW PERSISTING SALE on SALE SERVICE " + sale.toString());

    }

}
