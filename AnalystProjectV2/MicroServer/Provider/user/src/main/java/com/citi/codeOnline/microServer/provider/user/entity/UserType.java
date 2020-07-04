package com.citi.codeOnline.microServer.provider.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "userType")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class UserType{
    @Id
    private int typeId;

    private String typeName;
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
