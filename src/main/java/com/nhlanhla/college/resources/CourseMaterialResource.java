package com.nhlanhla.college.resources;

import com.nhlanhla.college.model.Course;
import com.nhlanhla.college.model.CourseMaterial;
import com.nhlanhla.college.repository.CourseMaterialRepository;
import com.nhlanhla.college.repository.CourseRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@Path("/api/course-material/v1")
public class CourseMaterialResource {
    Logger LOG = Logger.getLogger(CourseMaterialResource.class.getName());

    @Inject
    CourseMaterialRepository courseMaterialRepository;

    @GET
    public Response getAll() {
        List<CourseMaterial> movies = courseMaterialRepository.listAll();
        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return courseMaterialRepository.findByIdOptional(id)
                .map(obj -> Response.ok(obj).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(CourseMaterial u) {
        LOG.info(u.toString());
        courseMaterialRepository.persist(u);
        courseMaterialRepository.flush();
        if(courseMaterialRepository.isPersistent(u)) {
            return Response.created(URI.create("created")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = courseMaterialRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
