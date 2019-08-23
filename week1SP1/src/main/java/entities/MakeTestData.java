/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author stein
 */
public class MakeTestData {
        public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            BankCustomer b1 = new BankCustomer("Bob", "Saget", "1", 100.00, 1, "hidden");
            BankCustomer b2 = new BankCustomer("Peter", "Cooks", "1", 100.00, 1, "hidden");
            BankCustomer b3 = new BankCustomer("Steve", "Barbara", "1", 100.00, 1, "hidden");
            em.getTransaction().begin();
            em.persist(b1);
            em.persist(b2);
            em.persist(b3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
