package com.example.WishAndFish.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.WishAndFish.dto.JwtAuthenticationRequest;
import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.dto.UserTokenState;
import com.example.WishAndFish.exception.ResourceConflictException;
import com.example.WishAndFish.model.Client;
import com.example.WishAndFish.model.User;
import com.example.WishAndFish.service.*;
import com.example.WishAndFish.security.util.TokenUtils;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin()
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthentificationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private CottageOwnerService cottageOwnerService;

    @Autowired
    private BoatOwnerService boatOwnerService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private FishingInstructorService fishingInstructorService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState>createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        if(!user.isEnabled() || user.isDeleted()){
            return ResponseEntity.ok(null);
        }
        String jwt = tokenUtils.generateToken(user.getEmail(),user.getRole().getName());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userRequest,HttpServletRequest request) throws UnsupportedEncodingException{
        User existUser = this.userService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Email already exists");
        }

        if (userRequest.getRoleName().equals("ROLE_COTTAGE_OWNER")){
            User user = this.cottageOwnerService.save(userRequest);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else if(userRequest.getRoleName().equals("ROLE_CLIENT")){
            try{
                String randomCode = RandomString.make(64);
                userRequest.setVerificationCode(randomCode);
                User user = this.clientService.save(userRequest, getSiteURL(request));

                return new ResponseEntity<>(user, HttpStatus.CREATED);

            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else if(userRequest.getRoleName().equals("BOAT_OWNER")){
            User user = this.boatOwnerService.save(userRequest);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else if(userRequest.getRoleName().equals("FISHING_INSTRUCTOR")){
            User user = this.fishingInstructorService.save(userRequest);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else if(userRequest.getRoleName().equals("ADMIN")){
            User user = this.adminService.save(userRequest);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verify/{code}")
    public String verifyUser(@PathVariable String code) {
        if (userService.verify(code)) {
            return "success";
        } else {
            return "failed";
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
