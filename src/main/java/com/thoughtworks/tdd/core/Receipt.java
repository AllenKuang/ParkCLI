package com.thoughtworks.tdd.core;

import java.util.UUID;
public class Receipt {
    private String id;
    public Receipt(){
        setId();
    }

    public String getId() {
        return id;
    }
    public void setId() {
        this.id=UUID.randomUUID().toString();;
    }
}
