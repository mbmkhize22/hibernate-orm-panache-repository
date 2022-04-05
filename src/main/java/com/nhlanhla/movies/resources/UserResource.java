package com.nhlanhla.movies.resources;

import com.nhlanhla.movies.model.Movie;
import com.nhlanhla.movies.model.User;
import com.nhlanhla.movies.repository.MovieRepository;
import com.nhlanhla.movies.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/users/v1")
public class UserResource {

    @Inject
    UserRepository userRepository;

    @GET
    public Response getAll() {
        List<User> movies = userRepository.listAll();
        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return userRepository.findByIdOptional(id)
                .map(user -> Response.ok(user).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(User u) {
        userRepository.persist(u);
        if(userRepository.isPersistent(u)) {
            return Response.created(URI.create("/users/" + u.getUserId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = userRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
