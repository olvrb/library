package com.oliver.library.Application.Entities.User;

import javax.persistence.Entity;

@Entity
public class Researcher extends Employee {
    public Researcher() {
    }

    @Override
    public int getMaxRent() {
        return 10;
    }
}
