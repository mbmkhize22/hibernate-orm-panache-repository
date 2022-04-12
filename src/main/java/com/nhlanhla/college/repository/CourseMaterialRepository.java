package com.nhlanhla.college.repository;

import com.nhlanhla.college.model.Course;
import com.nhlanhla.college.model.CourseMaterial;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CourseMaterialRepository implements PanacheRepository<CourseMaterial> {
}
