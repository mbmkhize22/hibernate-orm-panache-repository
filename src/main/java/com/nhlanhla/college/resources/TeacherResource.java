package com.nhlanhla.college.resources;

import com.nhlanhla.college.model.Teacher;
import com.nhlanhla.college.repository.TeacherRepository;
import com.nhlanhla.movies.model.BankATM;
import com.nhlanhla.movies.repository.BankAtmRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/teacher/v1")
public class TeacherResource {

    @Inject
    TeacherRepository teacherRepository;

    @GET
    public Response getAll() {
        List<Teacher> movies = teacherRepository.listAll();
        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return teacherRepository.findByIdOptional(id)
                .map(obj -> Response.ok(obj).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(Teacher u) {
        teacherRepository.persist(u);
        if(teacherRepository.isPersistent(u)) {
            return Response.created(URI.create("created")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = teacherRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
