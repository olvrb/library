package com.oliver.library.Application.Entities.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class GeneralUser extends User {

    @Override
    public int getMaxRent() {
        return 3;
    }
}
