package com.packtpub.mmj.user.domain.service;

import com.packtpub.mmj.user.domain.model.entity.Entity;
import com.packtpub.mmj.user.domain.model.entity.User;
import com.packtpub.mmj.user.domain.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends BaseService<User, String>
        implements UserService {

    private UserRepository<User, String> userRepository;


    @Autowired
    public UserServiceImpl(UserRepository<User, String> userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) throws Exception {
        if (userRepository.containsName(user.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", user.getName()));
        }

        if (user.getName() == null || "".equals(user.getName())) {
            throw new Exception("Booking name cannot be null or empty string.");
        }
        super.add(user);
    }


    @Override
    public Collection<User> findByName(String name) throws Exception {
        return userRepository.findByName(name);
    }


    @Override
    public void update(User user) throws Exception {
        userRepository.update(user);
    }


    @Override
    public void delete(String id) throws Exception {
        userRepository.remove(id);
    }


    @Override
    public Entity findById(String restaurantId) throws Exception {
        return userRepository.get(restaurantId);
    }


    @Override
    public Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
