package com.nhlanhla.movies.resources;

import com.nhlanhla.movies.model.BankATM;
import com.nhlanhla.movies.model.LeproUser;
import com.nhlanhla.movies.repository.BankAtmRepository;
import com.nhlanhla.movies.repository.LeproUserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/lepro-user/v1")
public class LeproUserResource {

    @Inject
    LeproUserRepository leproUserRepository;

    @GET
    public Response getAll() {
        List<LeproUser> movies = leproUserRepository.listAll();
        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return leproUserRepository.findByIdOptional(id)
                .map(obj -> Response.ok(obj).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(LeproUser u) {
        leproUserRepository.persist(u);
        if(leproUserRepository.isPersistent(u)) {
            return Response.created(URI.create("Lepro User created")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = leproUserRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
