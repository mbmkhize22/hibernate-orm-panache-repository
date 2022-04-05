package com.nhlanhla.movies.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;

    @OneToMany(targetEntity = Ticket.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cp_fk", referencedColumnName = "userId")
    private Collection<Ticket> tickets;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
