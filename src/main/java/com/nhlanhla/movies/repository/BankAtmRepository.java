package com.nhlanhla.movies.repository;

import com.nhlanhla.movies.model.BankATM;
import com.nhlanhla.movies.model.Ticket;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BankAtmRepository implements PanacheRepository<BankATM> {
}
