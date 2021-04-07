package com.oliver.library.Application.Entities;

import org.codehaus.jackson.map.Serializers;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
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
        return Id;
    }
}
