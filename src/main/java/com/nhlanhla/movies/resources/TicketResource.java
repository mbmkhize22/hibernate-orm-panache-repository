package com.nhlanhla.movies.resources;

import com.nhlanhla.movies.model.Ticket;
import com.nhlanhla.movies.repository.TicketRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/tickets/v1")
public class TicketResource {

    @Inject
    TicketRepository ticketRepository;

    @GET
    public Response getAll() {
        List<Ticket> movies = ticketRepository.listAll();
        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return ticketRepository.findByIdOptional(id)
                .map(t -> Response.ok(t).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }


    @POST
    @Transactional
    public Response create(Ticket t) {
        ticketRepository.persist(t);
        if(ticketRepository.isPersistent(t)) {
            return Response.created(URI.create("/movies/" + t.getTicketId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = ticketRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
