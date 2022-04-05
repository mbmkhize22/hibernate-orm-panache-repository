package com.nhlanhla.college.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    private String firstName;
    private String lastName;

    @OneToMany(targetEntity = Course.class)
    @JoinColumn(name = "teacherId", referencedColumnName = "teacherId")
    private List<Course> courses;
}
