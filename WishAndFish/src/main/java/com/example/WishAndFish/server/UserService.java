package com.example.WishAndFish.server;

import java.util.List;

import com.example.WishAndFish.model.User;
import com.example.WishAndFish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findOne(Integer id) {
        return userRepository.findById(id).orElseGet(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User exam) {
        return userRepository.save(exam);
    }

    public void remove(Integer id) {
        userRepository.deleteById(id);
    }
}
