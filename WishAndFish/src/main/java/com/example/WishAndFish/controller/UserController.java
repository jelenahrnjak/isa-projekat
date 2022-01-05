package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.ChangePasswordDTO;
import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.model.User;
import com.example.WishAndFish.security.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.WishAndFish.service.UserService;


@RestController
@RequestMapping(value = "api/users")
@CrossOrigin()
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping(value="getOne", method = RequestMethod.GET)
    public @ResponseBody UserDTO getUser(@RequestHeader("Authorization") String token){
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        User u = userService.findByEmail(email);
        return new UserDTO(u);
    }

    @RequestMapping(value="role", method = RequestMethod.GET)
    public @ResponseBody String getRole(@RequestHeader("Authorization") String token){
        return tokenUtils.getRoleFromToken(token.split(" ")[1]);
    }

    @RequestMapping(value="update", method = RequestMethod.PUT)
    public @ResponseBody UserDTO update(@RequestBody UserDTO u) {

        return userService.update(u);
    }

    @RequestMapping(value="changePassword", method = RequestMethod.PUT)
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String token, @RequestBody ChangePasswordDTO dto) {

        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        User user = userService.findByEmail(email);
        ChangePasswordDTO u = userService.updatePasswod(dto);
        if(u == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
    }
}
