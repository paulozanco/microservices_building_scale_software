package com.packtpub.mmj.restaurant.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;


public class Table extends BaseEntity<BigInteger> {

    private int capacity;


    public Table(@JsonProperty("name") String name, @JsonProperty("id") BigInteger id, @JsonProperty("capacity") int capacity) {
        super(id, name);
        this.capacity = capacity;
    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public int getCapacity() {
        return capacity;
    }


    @Override
    public String toString() {
        return new StringBuilder("{id: ").append(id).append(", name: ")
                .append(name).append(", capacity: ").append(capacity).append("}").toString();
    }

}
