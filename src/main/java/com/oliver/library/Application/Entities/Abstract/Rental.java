package com.oliver.library.Application.Entities.Abstract;


import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class Rental {
    private Date startDate = new Date();

    private boolean returned = false;

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

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public boolean returned() {
        return this.returned;
    }

    public RentalKey getId() {
        return this.id;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public RentalObject getRentalObject() {
        return this.rentalObject;
    }

    public User getUser() {
        return this.user;
    }

    // Calculate return date based on start data and rental period.
    public Date getReturnDate() {
        LocalDateTime date = this.startDate.toInstant()
                                           .atZone(ZoneId.systemDefault())
                                           .toLocalDateTime();
        int days = this.rentalObject.getRentalPeriod();
        date = date.plusDays(days);
        return Date.from(date.atZone(ZoneId.systemDefault())
                             .toInstant());

    }
}
