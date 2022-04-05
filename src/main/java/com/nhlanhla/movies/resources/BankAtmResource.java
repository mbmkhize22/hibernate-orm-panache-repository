package com.nhlanhla.movies.resources;

import com.nhlanhla.movies.model.BankATM;
import com.nhlanhla.movies.model.User;
import com.nhlanhla.movies.repository.BankAtmRepository;
import com.nhlanhla.movies.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/atm/v1")
public class BankAtmResource {

    @Inject
    BankAtmRepository bankAtmRepository;

    @GET
    public Response getAll() {
        List<BankATM> movies = bankAtmRepository.listAll();
        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return bankAtmRepository.findByIdOptional(id)
                .map(obj -> Response.ok(obj).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(BankATM u) {
        bankAtmRepository.persist(u);
        if(bankAtmRepository.isPersistent(u)) {
            return Response.created(URI.create("Bank created")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = bankAtmRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
