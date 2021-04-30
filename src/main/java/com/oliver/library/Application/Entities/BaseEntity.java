package com.oliver.library.Application.Entities;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {
    @Id
    protected String Id;

    public BaseEntity() {
        this.Id = UUID.randomUUID()
                      .toString();
    }

    public String getId() {
        return this.Id;
    }

    public void setId(String id) {
        this.Id = id;
    }
}