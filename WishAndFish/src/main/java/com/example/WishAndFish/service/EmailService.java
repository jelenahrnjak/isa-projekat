package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.DeclinedRegistrationDTO;
import com.example.WishAndFish.model.User;
import com.example.WishAndFish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    public void sendMailForVerfication(User user, String url) throws MessagingException, UnsupportedEncodingException {

        String text = "<br>Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>";
        String verifyURL = url + "/api/auth/verify?code=" + user.getVerificationCode();
        text = text.replace("[[URL]]", verifyURL);

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(user.getEmail());
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("Verify your Wish&Fish account");
        helper.setText("Hello " + user.getName() + " " + user.getSurname() + text, true);
        javaMailSender.send(mail);
    }

    public void sendMailForAcceptedRegistration(User user) throws MessagingException, UnsupportedEncodingException {

        String text = "<br>Your registration has been accepted! You can now log into your account!<br>";

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(user.getEmail());
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("Welcome to Wish&Fish community!");
        helper.setText("Hello " + user.getName() + " " + user.getSurname() + text, true);
        javaMailSender.send(mail);
    }

    public void sendMailForDeclinedRegistration(DeclinedRegistrationDTO declinedRegistration) throws MessagingException {
        String text = "<br>Your registration has been declined! :(<br> Reason: ";
        User user = userRepository.findByEmail(declinedRegistration.getUserEmail());
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(declinedRegistration.getUserEmail());
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("Declined registration");
        helper.setText("Hello " + user.getName() + " " + user.getSurname() + text + declinedRegistration.getMessage(), true);
        javaMailSender.send(mail);
    }

    public void sendEmailForNewAction(String userEmail, String cottageName) throws MessagingException {
        String text = "<br>There is a new action in cottage:<br>";
        User user = userRepository.findByEmail(userEmail);
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(userEmail);
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("New action");
        helper.setText("Hello " + user.getName() + " " + user.getSurname() + text + cottageName, true);
        javaMailSender.send(mail);

    }
}
