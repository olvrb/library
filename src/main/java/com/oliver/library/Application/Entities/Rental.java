package com.oliver.library.Application.Entities;



import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Rental extends BaseEntity {
    private boolean returned;
    // private Date startDate = new Date();


    @ManyToOne
    private RentalObject rentalObject;

    @ManyToOne
    private User user;

    public boolean returned() {
        return this.returned;
    }
}
