package com.nhlanhla.movies.resources;

import com.nhlanhla.movies.model.BankATM;
import com.nhlanhla.movies.model.LeproTaskOne;
import com.nhlanhla.movies.repository.BankAtmRepository;
import com.nhlanhla.movies.repository.LeproTaskOneRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/task1/v1")
public class LeproTaskOneResource {

    @Inject
    LeproTaskOneRepository leproTaskOneRepository;

    @GET
    public Response getAll() {
        List<LeproTaskOne> movies = leproTaskOneRepository.listAll();
        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return leproTaskOneRepository.findByIdOptional(id)
                .map(obj -> Response.ok(obj).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(LeproTaskOne u) {
        leproTaskOneRepository.persist(u);
        if(leproTaskOneRepository.isPersistent(u)) {
            return Response.created(URI.create("Task created")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = leproTaskOneRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
