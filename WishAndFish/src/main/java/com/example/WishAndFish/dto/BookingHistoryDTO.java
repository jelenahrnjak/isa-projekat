package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingHistoryDTO {

    private Long id;
    private String image;
    private String start;
    private String end;
    private String owner;
    private Double totalPrice;
    private Double beforePrice;
    private Double discount;
    private Boolean isAction;
    private String additionalServices;
    private String type;
    public String name;
    public String address;
    public Boolean commentedOwner;
    public Boolean commentedEntity;
    public Boolean complaintOwner;
    public Boolean complaintEntity;
    public Boolean inProgress;
    public Boolean soon;

    public BookingHistoryDTO(Reservation r, List<AdditionalServicesDTO> services) {

        this.id = r.getId();
        this.image = getImage(r.getAppointment());
        this.start = getDateWithTime(r.getAppointment().getStartDate());
        this.end = getDateWithTime(r.getAppointment().getEndDate());
        this.totalPrice = r.getTotalPrice();
        this.isAction = r.getAppointment().getIsAction();
        this.additionalServices = getAdditionalServices(services);
        this.type = getTypeOfProperty(r.getAppointment());
        this.name = getNameOfProperty(r.getAppointment());
        this.address = getAddressOfProperty(r.getAppointment());
        this.commentedOwner = r.getCommentedOwner();
        this.commentedEntity = r.getCommentedEntity();
        this.complaintOwner = r.getComplaintOwner();
        this.complaintEntity = r.getComplaintEntity();
        this.owner = getOwnerOfProperty(r.getAppointment());
        this.soon = false;
        if(r.getAppointment().getStartDate().isBefore(LocalDate.now().atTime(14,0))){
            this.inProgress = true;
        }else{
            this.inProgress = false;

            if(!r.getAppointment().getIsAction() && r.getAppointment().getStartDate().isBefore((LocalDate.now().plusDays(3)).atTime(14,0))){
                this.soon = true;
            }

            if(r.getAppointment().getIsAction() && r.getAppointment().getExpirationDate().isBefore((LocalDate.now().plusDays(3)).atTime(14,0))){
                this.soon = true;
            }
        }

    }

    public BookingHistoryDTO(Appointment r, List<AdditionalServicesDTO> services) {

        this.id = r.getId();
        this.image = getImage(r);
        this.start = getDateWithTime(r.getStartDate());
        this.end = getDateWithTime(r.getEndDate());
        this.totalPrice = r.getPrice();
        this.isAction = r.getIsAction();
        this.additionalServices = getAdditionalServices(services);
        this.type = getTypeOfProperty(r);
        this.name = getNameOfProperty(r);
        this.address = getAddressOfProperty(r);
        this.owner = getOwnerOfProperty(r);
        this.soon = false;

        if(r.getStartDate().isBefore(LocalDate.now().atTime(14,0))){
            this.inProgress = true;
        }else{
            this.inProgress = false;

            if(!r.getIsAction() && r.getStartDate().isBefore((LocalDate.now().plusDays(3)).atTime(14,0))){
                this.soon = true;
            }

            if(r.getIsAction() && r.getExpirationDate().isBefore((LocalDate.now().plusDays(3)).atTime(14,0))){
                this.soon = true;
            }
        }
    }

    private String getOwnerOfProperty(Appointment a) {

        if(a.getCottage()!=null){
            CottageOwner owner = a.getCottage().getCottageOwner();
            return owner.getName() + " " + owner.getSurname();

        }else if(a.getBoat() != null){
            BoatOwner owner = a.getBoat().getBoatOwner();
            return owner.getName() + " " + owner.getSurname();

        }else if(a.getFishingAdventure()!=null){
            FishingInstructor owner = a.getFishingAdventure().getFishingInstructor();
            return owner.getName() + " " + owner.getSurname();

        }

        return "";
    }

    public String getDateWithTime(LocalDateTime date){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String dateString = date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL));

        String time = date.format(dateTimeFormatter);

        return dateString + " at " + time;
    }

    private String getImage(Appointment a){
        if(a.getCottage()!=null){

            return a.getCottage().getCoverImage();

        }else if(a.getBoat() != null){

            return a.getBoat().getCoverImage();

        }else if(a.getFishingAdventure()!=null){

            return a.getFishingAdventure().getCoverImage();

        }

        return "";
    }


    private String getTypeOfProperty(Appointment a){
        if(a.getCottage()!=null){

            return "cottage";

        }else if(a.getBoat() != null){

            return "boat";

        }else if(a.getFishingAdventure()!=null){

            return "adventure";

        }

        return "";
    }

    private String getNameOfProperty(Appointment a){
        if(a.getCottage()!=null){

            return a.getCottage().getName();

        }else if(a.getBoat() != null){

            return a.getBoat().getName();

        }else if(a.getFishingAdventure()!=null){

            return a.getFishingAdventure().getName();

        }

        return "";
    }

    private String getAddressOfProperty(Appointment a){
        if(a.getCottage()!=null){

            return a.getCottage().getAddress().toString();

        }else if(a.getBoat() != null){

            return a.getBoat().getAddress().toString();

        }else if(a.getFishingAdventure()!=null){

            return a.getFishingAdventure().getAddress().toString();

        }

        return "";
    }

    private String getAdditionalServices(List<AdditionalServicesDTO> services) {

        if(services == null){
            return "There is no additional services for this reservation";
        }

        String ret = "";
        for(AdditionalServicesDTO a : services){
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
}
