package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.CottageOwnerRepository;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class CottageService {

    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserRepository userRepository;

    public List<CottageDTO> findAll() {

        List<CottageDTO> ret = new ArrayList<>();
        for(Cottage c : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            if(!c.isDeleted()){
            ret.add(new CottageDTO(c));}
        };

        return ret;
    }

    public List<CottageDTO> mapSearch(CottageDTO dto) {
        List<CottageDTO> ret = new ArrayList<>();

        for(Cottage c : search(dto)){

            ret.add(new CottageDTO(c));
        }

        return ret;
    }

    public List<Cottage> search(CottageDTO dto) {
        List<Cottage> ret = new ArrayList<>();
        double rating = 0;
        double price = 0;
        try{
            rating = Double.parseDouble(dto.getRating());
        }catch (Exception e){
            System.out.println("Error with parsing rating");
        }
        try{
            price = Double.parseDouble(dto.getPrice());
        }catch (Exception e){
            System.out.println("Error with parsing price");
        }
        for(Cottage c : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            if(checkCottageForSearch(c,dto,rating, price) && !c.isDeleted()){
                ret.add(c);}
        }

        return ret;
    }

    private boolean checkCottageForSearch(Cottage c, CottageDTO dto,double rating, double price){

        return checkStrings(c.getName(), dto.getName()) && checkStrings(c.getAddress().toString(), dto.getAddress()) && checkRating(c.getRating(), rating) && checkPrice(c.getPricePerDay(), price);
    }

    private boolean checkStrings(String cottage, String search){
        if(search==null){
            return true;
        }
        return search.isEmpty() || cottage.toLowerCase().contains(search.toLowerCase());
    }

    private boolean checkRating(Double cottage, Double search){
        return cottage >= search || search > 5;
    }

    private boolean checkPrice(Double cottage, Double search){
        return cottage <= search || cottage <= 0 || search == 0;
    }

    public Cottage addCottage(AddCottageDTO newCottage){
        Address address = new Address(newCottage.getAddress().getStreet(),newCottage.getAddress().getStreetNumber(),
                newCottage.getAddress().getPostalCode(),newCottage.getAddress().getCityName(),newCottage.getAddress().getCountryName(),
                newCottage.getAddress().getLongitude(),newCottage.getAddress().getLatitude());

        Cottage cottage = new Cottage(newCottage.getName(),newCottage.getDescription(),newCottage.getPrice(),address, null);
        User user = this.userRepository.findByEmail(newCottage.getOwnerEmail());

        for(CottageOwner c: this.cottageOwnerRepository.findAll()){
            if(c.getEmail().equals(user.getEmail())){
                cottage.setCottageOwner(c);
            }
        }
        cottage.setCoverImage(newCottage.getCoverImage());
        cottage.setNumberOfRooms(newCottage.getNumberOfRooms());
        cottage.setBedsPerRoom(newCottage.getBedsPerRoom());

        return this.cottageRepository.save(cottage);
    }

    public ResponseEntity<Long> deleteCottage(Long id){
        for(Cottage c: this.cottageRepository.findAll()){
            if(c.getId() == id) {
                for(Appointment a:c.getAppointments()){
                    if(a.getReserved()){
                        System.out.println("Rezervisano");
                        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
                    }
                }
                c.setDeleted(true);
                this.cottageRepository.save(c);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    public Cottage findCottage(Long id){
        return cottageRepository.findById(id).orElseGet(null);
    }


    public EditCottageDTO editBasicInfo(EditCottageDTO editedCottage){
        for (Cottage c: cottageRepository.findAll()){
            if(editedCottage.getId().equals(c.getId())){
                c.setName(editedCottage.getName());
                c.setDescription(editedCottage.getDescription());
                c.setPricePerDay(editedCottage.getPricePerDay());
                c.getAddress().setCityName(editedCottage.getAddress().getCityName());
                c.getAddress().setCountryName(editedCottage.getAddress().getCountryName());
                c.getAddress().setLatitude(editedCottage.getAddress().getLatitude());
                c.getAddress().setLongitude(editedCottage.getAddress().getLongitude());
                c.getAddress().setPostalCode(editedCottage.getAddress().getPostalCode());
                c.getAddress().setStreet(editedCottage.getAddress().getStreet());
                c.getAddress().setStreetNumber(editedCottage.getAddress().getStreetNumber());
                c.setNumberOfRooms(editedCottage.getNumberOfRooms());
                c.setBedsPerRoom(editedCottage.getBedsPerRoom());
                cottageRepository.save(c);
                return new EditCottageDTO(c);
            }
        }
        return null;
    }

    public List<CottageDTO> findAllClient(String email) {
        List<CottageDTO> ret = new ArrayList<>();
        for(Cottage c : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            CottageDTO cottage = new CottageDTO(c);
            cottage.setIsSubscribed(clientService.checkCottageExistence(email, c.getId()));
            if(!c.isDeleted()){
                ret.add(cottage);}

        };

        return ret;
    }

    public List<CottageDTO> searchClient(CottageDTO dto, String email) {

        List<CottageDTO> ret = new ArrayList<>();
        double rating = 0;
        double price = 0;
        try{
            rating = Double.parseDouble(dto.getRating());
        }catch (Exception e){
            System.out.println("Error with parsing rating");
        }
        try{
            price = Double.parseDouble(dto.getPrice());
        }catch (Exception e){
            System.out.println("Error with parsing price");
        }
        for(Cottage c : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){

            CottageDTO cottage = new CottageDTO(c);
            cottage.setIsSubscribed(clientService.checkCottageExistence(email, c.getId()));
            if(checkCottageForSearch(c,dto,rating, price) && !c.isDeleted()){
                ret.add(cottage);}
        }

        return ret;

    }

    public List<CottageDTO> searchAppointments(AppointmentSearchDTO criteria){

        CottageDTO cottage = new CottageDTO(criteria.getName(), criteria.getAddress(), criteria.getRating(), criteria.getPrice());
        AppointmentDTO appointment = new AppointmentDTO(criteria.getStartDate(), criteria.getEndDate(), criteria.getMaxPersons());


        List<Cottage> cottages = search(cottage);
        List<CottageDTO> ret = new ArrayList<>();

        for(Cottage c : cottages){

            if(findAppointments(c.getAppointments(), appointment)){
                ret.add(new CottageDTO(c));
            }

        }

        return ret;
    }

    private boolean findAppointments(Set<Appointment> appointments, AppointmentDTO criteria) {

        for(Appointment a : appointments){
            if(a.isDeleted() || a.getIsAction() || (criteria.getMaxPersons()!=null && criteria.getMaxPersons() > a.getMaxPersons())){
                continue;
            }
            if(a.getStartDate().toLocalDate().isAfter(criteria.getStartDate().toLocalDate())){
                continue;
            }
            if(a.getEndDate().toLocalDate().isBefore(criteria.getEndDate().toLocalDate())){
                continue;
            }
            return true;
        }

        return false;
    }
}
