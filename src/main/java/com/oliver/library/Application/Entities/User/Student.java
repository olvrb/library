package com.oliver.library.Application.Entities.User;

import javax.persistence.Entity;

@Entity
public class Student extends User {
    public Student(String name) {
        super(name);
    }
    public Student() {
        super();
        this.maxRent = 10;
    }
}
