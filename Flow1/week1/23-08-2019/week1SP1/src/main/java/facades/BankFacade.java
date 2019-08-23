package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankFacade {

    private static BankFacade instance;
    private static EntityManagerFactory emf;

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     *
     * customerDTO getCustomerByID(int id) List<customerDTO>
     * getCustomerByName(String name) BankCustomer addCustomer(BankCustomer
     * cust) List<BankCustomer> getAllBankCustomers()
     *
     */
    public static BankFacade getBankFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerByID(long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            BankCustomer bcust = em.find(BankCustomer.class, id);
            return new CustomerDTO(bcust);
        } finally {
            em.close();
        }
    }

    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery query = em.createQuery("select c from BankCustomer c where c.firstName = :name ", BankCustomer.class).setParameter("name", name);
            List<BankCustomer> bcustomer = query.getResultList();
            List<CustomerDTO> custDTO = new ArrayList<>();
            for (BankCustomer bcust : bcustomer) {
                custDTO.add(new CustomerDTO(bcust));
            }
            return custDTO;
        } finally {
            em.close();
        }
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }

    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery query = em.createQuery("select c from BankCustomer c", BankCustomer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
