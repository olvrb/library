package com.oliver.library.Application.Entities;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {
    @Id
    protected String id;

    public BaseEntity() {
        this.id = UUID.randomUUID()
                      .toString();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}