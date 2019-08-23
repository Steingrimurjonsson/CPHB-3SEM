/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author stein
 */
@Path("animal")
public class AnimalResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {

        return "Hello from my first web service";
    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomJson() {
        Animal pig = new Animal("Pig", 2002, "offoff");
        Animal horse = new Animal("Horse", 2011, "Heeiiiei");
        Animal lion = new Animal("Lion", 2017, "Rooaarr");
        Animal shark = new Animal("Shark", 2016, "Blubblub");
        List<Animal> animalList = new ArrayList<Animal>();
        animalList.add(pig);
        animalList.add(lion);
        animalList.add(shark);
        animalList.add(horse);
        int rnd = new Random().nextInt(animalList.size() - 1);
        String result = new Gson().toJson(animalList.get(rnd));
        return result;
    }
}
