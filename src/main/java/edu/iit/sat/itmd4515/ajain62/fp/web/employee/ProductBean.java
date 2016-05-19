/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.web.employee;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Product;
import edu.iit.sat.itmd4515.ajain62.fp.service.ProductService;
import edu.iit.sat.itmd4515.ajain62.fp.web.AbstractJSFBean;
import edu.iit.sat.itmd4515.ajain62.fp.web.LoginBean;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * Product Bean manages product operations
 *
 * @author Ankith Jain
 */
@Model
public class ProductBean extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(ProductBean.class.getName());

    @EJB
    private ProductService productService;

    private Product product;

    /**
     *
     */
    public ProductBean() {
        super();
    }

    @Inject
    private LoginBean loginBean;

    @PostConstruct
    private void postConstruct() {
        super.postContruct();

        product = new Product();
        LOG.info("Inside ProductBean PostConstruct Method " + product.toString());
    }

    /**
     *
     * @param product
     * @return
     */
    public String doDelete(Product product) {

        LOG.info("The product to delete is " + product.toString());
        productService.delete(product);
        LOG.info("The product is DELETED is " + product.toString());

        return "product";

    }

    /**
     *
     * @param product
     * @return
     */
    public String doUpdate(Product product) {
        this.product = product;
        LOG.info("UPDATING Product" + product.toString());

        return "addProd";
    }

    /**
     *
     * @return
     */
    public String executeSaveProduct() {

        if (this.product.getId() != null) {
            LOG.info("Executing update on " + this.product.toString());

            productService.update(product);
            LOG.info("Updated PRODUCT on " + this.product.toString());
            return "product";
        } else {

            LOG.info("CREATING NEW INSERTING Product" + product.toString());
            productService.create(product);

            LOG.info("PRODUCT CREATED " + product.toString());

            return "product";
        }

    }

    /**
     * Get the value of product
     *
     * @return the value of product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Set the value of product
     *
     * @param product new value of product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

}
