package com.example.WishAndFish.service;

import java.util.List;

import com.example.WishAndFish.dto.AddressDTO;
import com.example.WishAndFish.dto.ChangePasswordDTO;
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

    public UserDTO update(UserDTO user) {
        User updated = userRepository.findByEmail(user.getEmail());
        updated.setName(user.getName());
        updated.setSurname(user.getSurname());
        updated.setPhoneNumber(user.getPhoneNumber());
        AddressDTO a = user.getAddress();
        updated.setAddress(new Address(a.getStreet(),a.getStreetNumber(),a.getPostalCode(),a.getCityName(),a.getCountryName()));
        userRepository.save(updated);
        return user;
    }

    public ChangePasswordDTO updatePasswod(ChangePasswordDTO user) {
        User updated=userRepository.findByEmail(user.getEmail());
        if(!user.getPassword().equals(user.getPasswordRepeated())){
            return null;
        }
        updated.setPassword(user.getPassword());
        userRepository.save(updated);
        return user;
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) {
        User u = userRepository.findByEmail(email);
        return u;
    }
}
