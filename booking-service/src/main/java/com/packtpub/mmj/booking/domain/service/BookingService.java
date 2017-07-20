package com.packtpub.mmj.booking.domain.service;

import com.packtpub.mmj.booking.domain.model.entity.Booking;
import com.packtpub.mmj.booking.domain.model.entity.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public interface BookingService {


    public void add(Booking booking) throws Exception;


    public void update(Booking booking) throws Exception;


    public void delete(String id) throws Exception;


    public Entity findById(String id) throws Exception;


    public Collection<Booking> findByName(String name) throws Exception;


    public Collection<Booking> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
