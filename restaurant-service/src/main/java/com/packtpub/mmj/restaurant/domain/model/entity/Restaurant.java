package com.packtpub.mmj.restaurant.domain.model.entity;

import java.util.ArrayList;
import java.util.List;


public class Restaurant extends BaseEntity<String> {

    private List<Table> tables = new ArrayList<>();
    private String address;

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    
    public Restaurant(String name, String id, String address, List<Table> tables) {
        super(id, name);
        this.address = address;
        this.tables = tables;
    }

    
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    
    public List<Table> getTables() {
        return tables;
    }

    
    @Override
    public String toString() {
        return new StringBuilder("{id: ").append(id).append(", name: ")
                .append(name).append(", address: ").append(address).
                append(", tables: ").append(tables).append("}").toString();
    }
}
