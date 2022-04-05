package com.nhlanhla.movies.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class LeproTaskOne {
    @Id
    @GeneratedValue
    private Long taskId;
    private String taskName;

    @OneToMany(targetEntity = BankATM.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_task_fk", referencedColumnName = "taskId")
    private Collection<BankATM> bankATMS;
}
