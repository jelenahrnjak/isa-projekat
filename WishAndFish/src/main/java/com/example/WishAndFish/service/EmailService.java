package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.dto.DeclinedRegistrationDTO;
import com.example.WishAndFish.model.AdditionalService;
import com.example.WishAndFish.model.Reservation;
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
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdditionalServiceService additionalServiceService;

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

    public void sendEmailForNewActionBoat(String userEmail, String cottageName) throws MessagingException {
        String text = "<br>There is a new action in boat:<br>";
        User user = userRepository.findByEmail(userEmail);
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(userEmail);
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("New action");
        helper.setText("Hello " + user.getName() + " " + user.getSurname() + text + cottageName, true);
        javaMailSender.send(mail);

    }

    public void sendEmailForNewReservation(String userEmail, Reservation reservation) throws MessagingException {

        User user = userRepository.findByEmail(userEmail);
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(userEmail);
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("Reservation confirmed");

        String name = getNameOfProperty(reservation);
        String address= getAddressOfProperty(reservation);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String startDate = reservation.getAppointment().getStartDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL));
        String endDate = reservation.getAppointment().getEndDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL));

        String startTime = reservation.getAppointment().getStartDate().format(dateTimeFormatter);
        String endTime = reservation.getAppointment().getEndDate().format(dateTimeFormatter);

        String additionalServices = getAdditionalServices(reservation);
        String price = reservation.getTotalPrice().toString() + "€";


        String text = ", we want to thank you for your reservation! Here are some details: <br>" +
                "Reservation in: " + name + "<br>Address: " + address + "<br> Start date and time: " +
                startDate + " at " + startTime + "<br> End date and time: " + endDate + " at " + endTime +
                "<br>Additional services: " + additionalServices + "<br><br>Total price: " + price +
                "<br><br> Wish&Fish wishes you a pleasant vacation and great fun! :)";



        helper.setText("Hello " + user.getName() + " " + user.getSurname() + text, true);
        javaMailSender.send(mail);

    }

    private String getAdditionalServices(Reservation reservation) {
        String ret = "";
        for(AdditionalServicesDTO a : additionalServiceService.getAllByAppointment(reservation.getAppointment().getId())){
            ret = ret + a.getName() + " (" + a.getPrice() + "€)" + ", ";
        }
        if(ret.equals("")){
            ret = " There is no additional services for this reservation";
        }else{
            ret.trim();
            ret = ret.substring(0, ret.length() -2);
        }

        return ret;
    }

    private String getNameOfProperty(Reservation reservation){
        if(reservation.getAppointment().getCottage()!=null){

            return reservation.getAppointment().getCottage().getName();

        }else if(reservation.getAppointment().getBoat() != null){

            return reservation.getAppointment().getBoat().getName();

        }else if(reservation.getAppointment().getFishingAdventure()!=null){

            return reservation.getAppointment().getFishingAdventure().getName();

        }

        return "";
    }

    private String getAddressOfProperty(Reservation reservation){
        if(reservation.getAppointment().getCottage()!=null){

            return reservation.getAppointment().getCottage().getAddress().toString();

        }else if(reservation.getAppointment().getBoat() != null){

            return reservation.getAppointment().getBoat().getAddress().toString();

        }else if(reservation.getAppointment().getFishingAdventure()!=null){

            return reservation.getAppointment().getFishingAdventure().getAddress().toString();

        }

        return "";
    }
}
