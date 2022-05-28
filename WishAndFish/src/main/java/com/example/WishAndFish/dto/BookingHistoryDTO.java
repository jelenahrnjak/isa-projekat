package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.AdditionalService;
import com.example.WishAndFish.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingHistoryDTO {

    private Long id;
    private String image;
    private String start;
    private String end;
    private Double totalPrice;
    private Boolean isAction;
    private String additionalServices;
    private String type;
    public String name;
    public String address;
    public Boolean commentedOwner;
    public Boolean commentedEntity;

    public BookingHistoryDTO(Reservation r) {

        this.id = r.getId();
        this.image = getImage(r);
        this.start = getDateWithTime(r.getAppointment().getStartDate());
        this.end = getDateWithTime(r.getAppointment().getEndDate());
        this.totalPrice = r.getTotalPrice();
        this.isAction = r.getAppointment().getIsAction();
        this.additionalServices = getAdditionalServices(r);
        this.type = getTypeOfProperty(r);
        this.name = getNameOfProperty(r);
        this.address = getAddressOfProperty(r);
        this.commentedOwner = r.getCommentedOwner();
        this.commentedEntity = r.getCommentedEntity();
    }

    public String getDateWithTime(LocalDateTime date){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String dateString = date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL));

        String time = date.format(dateTimeFormatter);

        return dateString + " at " + time;
    }

    private String getImage(Reservation reservation){
        if(reservation.getAppointment().getCottage()!=null){

            return reservation.getAppointment().getCottage().getCoverImage();

        }else if(reservation.getAppointment().getBoat() != null){

            return reservation.getAppointment().getBoat().getCoverImage();

        }else if(reservation.getAppointment().getFishingAdventure()!=null){

            return reservation.getAppointment().getFishingAdventure().getCoverImage();

        }

        return "";
    }


    private String getTypeOfProperty(Reservation reservation){
        if(reservation.getAppointment().getCottage()!=null){

            return "cottage";

        }else if(reservation.getAppointment().getBoat() != null){

            return "boat";

        }else if(reservation.getAppointment().getFishingAdventure()!=null){

            return "adventure";

        }

        return "";
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

    private String getAdditionalServices(Reservation reservation) {

        if(reservation.getAppointment().getAdditionalServices() == null){
            return "There is no additional services for this reservation";
        }

        String ret = "";
        for(AdditionalService a : reservation.getAppointment().getAdditionalServices()){
            ret = ret + a.getName() + " (" + a.getPrice() + "€)" + ", ";
        }
        if(ret.equals("")){
            ret = " There is no additional services for this reservation";
        }else{
            ret.trim();
            ret = ret.substring(0, ret.length() -1);
        }

        return ret;
    }
}
