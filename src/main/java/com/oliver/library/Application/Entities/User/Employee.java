package com.oliver.library.Application.Entities.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee extends User {
    @OneToMany
    private Set<User> supervisees = new HashSet<>();

    @ManyToOne
    private User supervisor;

    public Employee(String name, String ssn, String password) {
        super(name, ssn, password);
    }

    public Employee() {
    }

    public Set<User> getSupervisees() {
        return supervisees;
    }

    public User getSupervisor() {
        return supervisor;
    }

    @Override
    public int getMaxRent() {
        return 7;
    }
}
