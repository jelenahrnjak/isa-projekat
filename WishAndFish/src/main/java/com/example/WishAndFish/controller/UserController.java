package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.ChangePasswordDTO;
import com.example.WishAndFish.dto.DeclinedRegistrationDTO;
import com.example.WishAndFish.dto.RequestDTO;
import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.model.User;
import com.example.WishAndFish.security.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.WishAndFish.service.UserService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RequestMapping(value = "api/users")
@RestController
@CrossOrigin()
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<UserDTO> getUsers(@RequestHeader("Authorization") String token){
        return userService.getAllUsers();
    }

    @RequestMapping(value="/getUnenabledUsers", method = RequestMethod.GET)
    public @ResponseBody
    List<UserDTO> getUnenabledUsers(@RequestHeader("Authorization") String token){
        return userService.getUnenabledUsers();
    }

    @RequestMapping(value="/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER') || hasAuthority('ROLE_BOAT_OWNER') || hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email){
        User u = userService.findByEmail(email);

        if(u==null || u.isDeleted()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO dto = new UserDTO(u);
        dto.setRequestedDeletion(userService.isRequestedDeletion(u));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @RequestMapping(value="role", method = RequestMethod.GET)
    public @ResponseBody String getRole(@RequestHeader("Authorization") String token){
        return tokenUtils.getRoleFromToken(token.split(" ")[1]);
    }

    @RequestMapping(value="update", method = RequestMethod.PUT)
    public @ResponseBody UserDTO update(@RequestBody UserDTO u) {

        return userService.update(u);
    }

    @RequestMapping(value="enableUser", method = RequestMethod.PUT)
    public ResponseEntity<String> acceptUser(@RequestBody String email) throws MessagingException, UnsupportedEncodingException {
        userService.enableUser(email);
        userService.sendMailForAcceptedRegistration(email);
        return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="declineUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> declineUser(@RequestBody DeclinedRegistrationDTO declinedRegistration) throws MessagingException, UnsupportedEncodingException {
        userService.declineUser(declinedRegistration);
        return new ResponseEntity<>("User declined",HttpStatus.OK);
    }

    @RequestMapping(value="changePassword", method = RequestMethod.PUT)
    public ResponseEntity<ChangePasswordDTO> changePassword(@RequestBody ChangePasswordDTO dto) {

        User user = userService.findByEmail(dto.getEmail());
        ChangePasswordDTO u = userService.updatePasswod(dto);
        if(u == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    @RequestMapping(value="/requestDeleting", method = RequestMethod.PUT)
    public ResponseEntity<RequestDTO> requestDeleting(@RequestBody RequestDTO rq) {

        RequestDTO r = userService.requestDeleting(rq.getEmail(),rq.getReason());
        if(r == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
}
