package com.example.WishAndFish.server;

import java.util.List;

import com.example.WishAndFish.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.WishAndFish.repository.KorisnikRepository;

@Service
public class UserService {

    @Autowired
    private KorisnikRepository examRepository;

    public User findOne(Integer id) {
        return examRepository.findById(id).orElseGet(null);
    }

    public List<User> findAll() {
        return examRepository.findAll();
    }

    public User save(User exam) {
        return examRepository.save(exam);
    }

    public void remove(Integer id) {
        examRepository.deleteById(id);
    }
}
