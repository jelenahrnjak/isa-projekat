package com.example.WishAndFish.service;

import java.util.List;

import com.example.WishAndFish.dto.AddressDTO;
import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.repository.UserRepository;
import com.example.WishAndFish.model.Address;
import com.example.WishAndFish.model.Role;
import com.example.WishAndFish.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findOne(Long id) {
        return userRepository.findById(id).orElseGet(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(UserDTO requestUser) {
        AddressDTO a = requestUser.getAddress();
        User user = new User(passwordEncoder.encode(requestUser.getPassword()), requestUser.getEmail(), requestUser.getName(), requestUser.getSurname(), requestUser.getPhoneNumber());
        Address address = new Address(a.getStreet(),a.getStreetNumber(),a.getPostalCode(),a.getCityName(), a.getCountryName(),a.getLongitude(),a.getLatitude());
        user.setAddress(address);
        user.setVerificationCode(requestUser.getVerificationCode());

        Role role = roleService.findByName(requestUser.getRoleName());
        user.setRole(role);

        return this.userRepository.save(user);
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }

    }

    public void update(UserDTO user) {
        User updated = userRepository.findByEmail(user.getEmail());
        updated.setName(user.getName());
        updated.setSurname(user.getSurname());
        updated.setPhoneNumber(user.getPhoneNumber());
        AddressDTO a = user.getAddress();
        updated.setAddress(new Address(a.getStreet(),a.getStreetNumber(),a.getPostalCode(),a.getCityName(),a.getCountryName()));
        userRepository.save(user);
    }

    public void updatePasswod(UserDTO user) {
        User updated=userRepository.findByEmail(user.getEmail());
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) { return userRepository.findByEmail(email);}
}
