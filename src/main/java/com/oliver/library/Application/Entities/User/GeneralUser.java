package com.oliver.library.Application.Entities.User;

import javax.persistence.Entity;

@Entity
public class GeneralUser extends User {

    public GeneralUser(String name, String ssn, String password) {
        super(name, ssn, password);
    }

    public GeneralUser() {
    }

    @Override
    public int getMaxRent() {
        return 3;
    }
}
