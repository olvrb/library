package com.oliver.library.Application.Entities.Abstract;


import com.oliver.library.Application.Entities.BaseEntity;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Rental extends BaseEntity implements Serializable {
    @EmbeddedId
    private RentalKey id;


    private boolean returned;
    private Date startDate = new Date();


    @ManyToOne
    @MapsId("rentalObjectId")
    @JoinColumn(name = "rental_object_id")
    private RentalObject rentalObject;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    public boolean returned() {
        return this.returned;
    }
}
