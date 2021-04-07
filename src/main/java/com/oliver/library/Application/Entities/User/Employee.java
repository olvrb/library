package com.oliver.library.Application.Entities.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee extends User {
    public Employee() {
    }

    @OneToMany
    private Set<User> supervisees = new HashSet<>();

    @ManyToOne
    private User supervisor;

    @Override
    public int getMaxRent() {
        return 7;
    }
}
