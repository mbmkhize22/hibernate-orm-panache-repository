package com.nhlanhla.movies.repository;

import com.nhlanhla.movies.model.Movie;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {

    public List<Movie> findByCountry(String country) {
        return list("SELECT m FROM Movie m WHERE m.country = ?1 ORDER BY DESC", country);
    }
}
