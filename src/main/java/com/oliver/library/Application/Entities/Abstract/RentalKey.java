package com.oliver.library.Application.Entities.Abstract;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RentalKey implements Serializable {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "rental_object_id")
    private String rentalObjectId;
}
