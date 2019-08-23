/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author stein
 */
public class EntityTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer c1 = new Customer("bob", "rogan");
        Customer c2 = new Customer("steve", "joehans");
        Customer c3 = new Customer("page", "barbra");
        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.getTransaction().commit();
            System.out.println(c1.getId());
            System.out.println(c2.getId());
            System.out.println(c3.getId());
        } finally {
            em.close();
        }
    }
}
