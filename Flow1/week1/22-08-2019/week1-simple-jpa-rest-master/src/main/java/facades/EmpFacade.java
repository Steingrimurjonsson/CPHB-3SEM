package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class EmpFacade {

    private static EmpFacade instance;
    private static EntityManagerFactory emf;
    private EmpFacade() {}

    public static EmpFacade getEmpFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmpFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery query = em.createQuery("select e from Employee e where e.name = :name ", Employee.class).setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery query = em.createQuery("select e from Employee e", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public  List<Employee> getEmployeeWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(z.salary) FROM Employee z)", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee createEmployee(String name, String address, double salary) {
        Employee e = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return e;
        } finally {
            em.close();
        }
    }
       public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EmpFacade ef = EmpFacade.getEmpFacade(emf);
        //create employee and more TODO
        List<Employee> employees = ef.getAllEmployees();
    }

}
