package com.packtpub.mmj.restaurant.domain.valueobject;

import com.packtpub.mmj.restaurant.domain.model.entity.Table;
import java.util.ArrayList;
import java.util.List;


public class RestaurantVO {

    private List<Table> tables = new ArrayList<>();
    private String name;
    private String id;
    private String address;


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public RestaurantVO() {
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
