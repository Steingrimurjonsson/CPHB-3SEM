/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import entity.Customer;
/**
 *
 * @author stein
 */
public class CustomerFacade {
        private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    public CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer c = em.find(Customer.class, id);
            return c;
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery q1 = em.createQuery("Select c from Customer c where c.lastName = '" + lastName + "'", Customer.class);
            return q1.getResultList();
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q1 = em.createQuery("SELECT COUNT(c) FROM Customer c");
            Long l = (Long) q1.getSingleResult();
            return l.intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Customer> allCustomers(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery q1 = em.createQuery("Select c from Customer c", Customer.class);
            return q1.getResultList();
        }finally{
            em.close();
        }
    }
    
    public Customer addCustomer(String firstName, String lastName){
        EntityManager em = emf.createEntityManager();
        try{
            Customer c = new Customer(firstName, lastName);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        }finally{
            em.close();
        }
    }
    /*
public static void main(String[] args) {
      
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);/*
        List<Customer> customers = cf.findByLastName("rogan");
        System.out.println(customers.isEmpty());
        System.out.println(customers.get(0).getFirstName());
        System.out.println(cf.getNumberOfCustomers());
        List<Customer> allCustomers = cf.allCustomers();
        for (Customer customer : allCustomers) {
            System.out.println(customer);
        }
        *//*
    Customer cust = cf.addCustomer("Charlie", "Chesterfield");
    }
 */
}
