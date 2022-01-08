package com.example.WishAndFish.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.example.WishAndFish.dto.AddressDTO;
import com.example.WishAndFish.dto.ChangePasswordDTO;
import com.example.WishAndFish.dto.DeclinedRegistrationDTO;
import com.example.WishAndFish.dto.RequestDTO;
import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    LoyaltyCategoryRepository loyaltyCategoryRepository;

    @Autowired
    RequestForDeletingRepository requestForDeletingRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

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
        user.setLoyaltyCategory(loyaltyCategoryRepository.findByLevel(1));
        user.setVerificationCode(requestUser.getVerificationCode());
        user.setReasonForRegistration(requestUser.getReasonForRegistration());

        Role role = roleRepository.findByName(requestUser.getRoleName());
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
        Address newAddress = updated.getAddress();
        newAddress.setStreet(a.getStreet());
        newAddress.setStreetNumber(a.getStreetNumber());
        newAddress.setPostalCode(a.getPostalCode());
        newAddress.setCityName(a.getCityName());
        newAddress.setCountryName(a.getCountryName());
        addressRepository.save(newAddress);
        userRepository.save(updated);
        return user;
    }

    public ChangePasswordDTO updatePasswod(ChangePasswordDTO user) {
        User updated=userRepository.findByEmail(user.getEmail());
        if(!user.getPassword().equals(user.getPasswordRepeated()) || !user.getOldPassword().equals(updated.getPassword())){
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

    public List<UserDTO> getAllUsers(){
        List<UserDTO> users = new ArrayList<>();
        for(User u : findAll()){
            users.add(new UserDTO((u)));
        }
        return users;
    }

    public List<UserDTO> getUnenabledUsers(){
        List<UserDTO> unenabledUsers = new ArrayList<>();
        for (User u : findAll()){
            if (!u.isEnabled() && !u.getRole().getName().equals("ROLE_CLIENT")){
                unenabledUsers.add(new UserDTO(u));
            }
        }
        return unenabledUsers;
    }

    public void enableUser(String email){
        User u = findByEmail(email);
        u.setEnabled(true);
        userRepository.save(u);
    }

    public void sendMailForAcceptedRegistration(String email) throws MessagingException, UnsupportedEncodingException {
        User u = findByEmail(email);
        emailService.sendMailForAcceptedRegistration(u);
    }

    public void declineUser(DeclinedRegistrationDTO declinedRegistration) throws MessagingException {
        User u = findByEmail(declinedRegistration.getUserEmail());
        emailService.sendMailForDeclinedRegistration(declinedRegistration);
        userRepository.deleteById(u.getId());
    }
  
    public RequestDTO requestDeleting(String email, String reason) {
        User u = findByEmail(email);
        if(isRequestedDeletion(u)){
            return null;
        }
        RequestForDeleting newRequest = new RequestForDeleting(reason,false,u);
        requestForDeletingRepository.save(newRequest);
        return new RequestDTO(email,reason);
    }

    public boolean isRequestedDeletion(User u){
        RequestForDeleting rq = requestForDeletingRepository.findByUser(u);
        if(rq == null){
            return false;
        } else if(rq.isApproved() || (!rq.isApproved() && rq.isProcessed()) || !rq.isProcessed()){
            return true;
        }
        return false;
    }
}
