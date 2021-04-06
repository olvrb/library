package com.oliver.library.Application.Entities;

import org.activejpa.entity.Model;
import org.codehaus.jackson.map.Serializers;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity extends Model {
    @Id
    protected String Id;

    public BaseEntity() {
        this.Id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return Id;
    }
}
