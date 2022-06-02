package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AddressDTO;
import com.example.WishAndFish.dto.CottageDisplayDTO;
import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CottageOwnerService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    LoyaltyCategoryRepository loyaltyCategoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;

    @Autowired
    private CottageRepository cottageRepository;

    public User save(UserDTO requestUser) {
        AddressDTO a = requestUser.getAddress();
        CottageOwner user = new CottageOwner(passwordEncoder.encode(requestUser.getPassword()), requestUser.getEmail(), requestUser.getName(), requestUser.getSurname(), requestUser.getPhoneNumber());
        Address address = new Address(a.getStreet(),a.getStreetNumber(),a.getPostalCode(),a.getCityName(), a.getCountryName(),a.getLongitude(),a.getLatitude());
        user.setAddress(address);
        user.setLoyaltyCategory(loyaltyCategoryRepository.findByLevel(1));
        user.setVerificationCode(requestUser.getVerificationCode());
        user.setReasonForRegistration(requestUser.getReasonForRegistration());
        user.setEnabled(true);
        Role role = roleRepository.findByName(requestUser.getRoleName());
        user.setRole(role);

        return this.cottageOwnerRepository.save(user);
    }

    public List<CottageDisplayDTO> getCottagesFromOwner(String email){
        List<CottageDisplayDTO> ret = new ArrayList<>();
        for(Cottage c: cottageRepository.findAll()){
            User u = userRepository.findByEmail(c.getCottageOwner().getEmail());
            if(u.getEmail().equals(email) && !c.isDeleted()){
                ret.add(new CottageDisplayDTO(c));
            }
        }
        return  ret;
    }

}
