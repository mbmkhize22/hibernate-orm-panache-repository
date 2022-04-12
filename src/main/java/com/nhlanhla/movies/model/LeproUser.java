package com.nhlanhla.movies.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity*/
public class LeproUser {
    //@Id
    //@GeneratedValue
    private Long leproUserId;
    private String fullName;
}
