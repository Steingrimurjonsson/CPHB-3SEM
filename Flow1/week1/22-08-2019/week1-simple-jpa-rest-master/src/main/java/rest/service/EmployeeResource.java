package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmpFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("emp")
public class EmployeeResource {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EmpFacade facade =  EmpFacade.getEmpFacade(emf);
    private static Gson gson = new Gson();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{it works}";
    }
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        List<Employee> employees = facade.getAllEmployees();
        List<EmployeeDTO> emplist = new ArrayList<EmployeeDTO>();
        for (Employee employee : employees) {
            emplist.add(new EmployeeDTO(employee));
        }
        return gson.toJson(emplist);
    }
     @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id") int id) {
        Employee e = facade.getEmployeeById(id);
        return gson.toJson(new EmployeeDTO(e));
    }
    
    @GET
    @Path("hp")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaidEmployee(){
        
         return "{it works}";
    }
    
    @GET
    @Path("name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeesByName(@PathParam("name") String name){
        List<Employee> employees = facade.getEmployeesByName(name);
        List<EmployeeDTO> emplist = new ArrayList<>();
        for (Employee employee : employees) {
            emplist.add(new EmployeeDTO(employee));
        }
        return gson.toJson(emplist);
    }
    
/*
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
*/
}
