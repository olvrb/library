package com.oliver.library.Application.Entities.Abstract;


import com.oliver.library.Application.Entities.BaseEntity;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Rental implements Serializable {
    private final boolean returned = false;

    private final Date startDate = new Date();

    @EmbeddedId
    private RentalKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("rentalObjectId")
    @JoinColumn(name = "rental_object_id")
    private RentalObject rentalObject;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;


    public Rental(RentalObject rentalObject, User user) {
        this.rentalObject = rentalObject;
        this.user = user;
        this.id = new RentalKey(rentalObject.getId(), user.getId());
    }

    public Rental() {

    }

    public boolean returned() {
        return this.returned;
    }

    public RentalKey getId() {
        return id;
    }

    public boolean isReturned() {
        return returned;
    }

    public Date getStartDate() {
        return startDate;
    }

    public RentalObject getRentalObject() {
        return rentalObject;
    }

    public User getUser() {
        return user;
    }

    public Date getReturnDate() {
        LocalDateTime date = this.startDate.toInstant()
                                           .atZone(ZoneId.systemDefault())
                                           .toLocalDateTime();
        date.plusDays(this.rentalObject.getRentalPeriod());
        return Date.from(date.atZone(ZoneId.systemDefault())
                             .toInstant());

    }
}
