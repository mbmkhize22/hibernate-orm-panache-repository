package com.nhlanhla.college.resources;

import com.nhlanhla.college.model.Course;
import com.nhlanhla.college.repository.CourseRepository;
import com.nhlanhla.movies.model.BankATM;
import com.nhlanhla.movies.repository.BankAtmRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/course/v1")
public class CourseResource {

    @Inject
    CourseRepository courseRepository;

    @GET
    public Response getAll() {
        List<Course> movies = courseRepository.listAll();
        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return courseRepository.findByIdOptional(id)
                .map(obj -> Response.ok(obj).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(Course u) {
        courseRepository.persist(u);
        if(courseRepository.isPersistent(u)) {
            return Response.created(URI.create("created")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = courseRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
