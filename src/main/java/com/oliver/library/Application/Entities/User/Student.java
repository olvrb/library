package com.oliver.library.Application.Entities.User;

import javax.persistence.Entity;

@Entity
public class Student extends User {
    public Student(String name, String ssn, String password) {
        super(name, ssn, password);
    }

    public Student() {
    }

    @Override
    public int getMaxRent() {
        return 5;
    }
}
