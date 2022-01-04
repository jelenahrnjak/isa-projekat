package com.example.WishAndFish.service;

import com.example.WishAndFish.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
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

    public void sendMailForVerfication(User user, String url) throws MessagingException, UnsupportedEncodingException {

        String text = "<br>Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>";
        String verifyURL = url + "/api/auth/verify?code=" + user.getVerificationCode();
        System.out.println("url " + verifyURL);
        text = text.replace("[[URL]]", verifyURL);

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(user.getEmail());
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("Verify your Wish&Fish account");
        helper.setText("Hello " + user.getName() + " " + user.getSurname() + text, true);
        javaMailSender.send(mail);
    }

}
