package com.packtpub.mmj.user.domain.service;

import com.packtpub.mmj.user.domain.model.entity.User;
import com.packtpub.mmj.user.domain.model.entity.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public interface UserService {


    public void add(User booking) throws Exception;


    public void update(User booking) throws Exception;


    public void delete(String id) throws Exception;


    public Entity findById(String restaurantId) throws Exception;


    public Collection<User> findByName(String name) throws Exception;


    public Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
