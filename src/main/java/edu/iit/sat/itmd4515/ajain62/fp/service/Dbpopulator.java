/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.fp.service;

import edu.iit.sat.itmd4515.ajain62.fp.domain.Address;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Customer;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Employee;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Orders;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Product;
import edu.iit.sat.itmd4515.ajain62.fp.domain.Sale;
import edu.iit.sat.itmd4515.ajain62.fp.domain.security.Group;
import edu.iit.sat.itmd4515.ajain62.fp.domain.security.User;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * Pops up Sample Data for testing application
 * @author Ankith Jain

 */
@Singleton
@Startup
public class Dbpopulator {

    /**
     *
     */
    public Dbpopulator() {
    }

    @EJB
    private UserService userService;

    @EJB
    private AddressService addressService;

    @EJB
    private ProductService productService;

    @EJB
    private CustomerService customerService;

    @EJB
    private EmployeeService employeeService;

    @EJB
    private OrderService orderService;

    @EJB
    private SaleService saleService;

    @EJB
    private GroupService groupService;

    /**
     *
     */
    @PostConstruct
    public void dbSeed() {

        // Sample data for Application is persisted here
        // Creating Group
        Group employeeGroup = new Group("Employees", "Group of Employee");

        Group customerGroup = new Group("Customers", "Group of Customer");

        Group adminGroup = new Group("SystemAdmin", "Group of Admin");

        groupService.create(employeeGroup);

        groupService.create(customerGroup);

        groupService.create(adminGroup);

        // User
        User u1 = new User("demo", "demo");
        User u2 = new User("demoEMP", "demo");
        User u3 = new User("demoCUST", "demo");
        User u4 = new User("demo1", "demo");
        User Cust = new User("demoCUST1", "demo");
        User admin1 = new User("admin", "admin");

        u1.addGroup(employeeGroup);
        u2.addGroup(employeeGroup);
        u3.addGroup(customerGroup);
        u4.addGroup(employeeGroup);
        u4.addGroup(customerGroup);
        Cust.addGroup(customerGroup);
        admin1.addGroup(adminGroup);

        userService.create(u4);
        userService.create(admin1);

        Address a1 = new Address("Chicago", "IL", "UnitedStates", "60616", "C");
        Address a2 = new Address("Orlando", "FL", "UnitedStates", "70613", "C");

        addressService.create(a1);
        addressService.create(a2);

        Address ea1 = new Address("EMPLlChicago", "IL", "NorthAmerica", "78616", "E");
        Address ea2 = new Address("EMPLMEXICO", "MEX", "SouthAmerica", "80416", "E");

        addressService.create(ea1);
        addressService.create(ea2);
        Employee e1 = new Employee("Emp Raj", "Emp Rani", "dfj@emp.com", "5554474541");
        employeeService.create(e1);
        e1.setAddress(ea1);
        e1.setUser(u1);
        userService.create(u1);

        Employee e2 = new Employee("Sohan EMP", "Kumar EMP", "sohan@emp.com", "7055675612");
        employeeService.create(e2);
        e2.setAddress(ea2);
        e2.setUser(u2);
        userService.create(u2);

        Customer c1 = new Customer("Kumarpal", "Jain", "amjain90@gmail.com", "3129374813");
        Customer c2 = new Customer("MahendraKumar", "Kurnool", "amjain90@us.ibm.com", "4566554544");

        c1.setAddress(a1);
        c1.setUser(u3);
        customerService.create(c1);
        userService.create(u3);

        c2.setAddress(a2);
        c2.setUser(Cust);
        customerService.create(c2);
        userService.create(Cust);

        Product p1 = new Product("FP Gold Ring", "Gold", 100);
        productService.create(p1);

        Product p2 = new Product("NormDiamondRing", "Diamond", 500);
        productService.create(p2);
        Product p3 = new Product("PlatinumRing", "Platinum", 1000);
        productService.create(p3);

        Orders o1 = new Orders("Good style", "PROCESSED", 1600);
        o1.setCustomer(c1);

        o1.addProducts(p1);

        o1.addProducts(p2);

        o1.addProducts(p3);

        orderService.create(o1);
        Orders o2 = new Orders("Second style", "OPEN", 1500);
        o2.setCustomer(c2);
        o2.addProducts(p2);
        o2.addProducts(p3);
        orderService.create(o2);

        Orders o3 = new Orders("Third style", "OPEN", 100);
        o3.setCustomer(c2);
        o3.addProducts(p1);
        orderService.create(o3);

        Sale s1 = new Sale("PROCESSED");
        s1.setOrders(o1);
        s1.setEmpsale(e1);
        s1.setCustomer(c1);

        saleService.create(s1);

    }

}
