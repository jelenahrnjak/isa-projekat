package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.BoatOwnerRepository;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.ClientRepository;
import com.example.WishAndFish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BoatService {

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private BoatOwnerRepository boatOwnerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    public List<BoatDTO> findAll() {

        List<BoatDTO> ret = new ArrayList<>();
        for(Boat b : boatRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            if(!b.isDeleted()){
                ret.add(new BoatDTO(b));}

        }

        return ret;
    }

    public List<BoatDTO> findAllClient(String email) {

        List<BoatDTO> ret = new ArrayList<>();
        for(Boat b : boatRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            BoatDTO boat = new BoatDTO(b);
            boat.setIsSubscribed(clientService.checkBoatExistence(email, b.getId()));
            if(!b.isDeleted()){
                ret.add(boat);}

        }

        return ret;
    }

    public List<BoatDTO> mapSearch(BoatDTO dto) {
        List<BoatDTO> ret = new ArrayList<>();

        for(Boat b : search(dto)){

            ret.add(new BoatDTO(b));
        }

        return ret;
    }

    public List<Boat> search(BoatDTO dto) {
        List<Boat> ret = new ArrayList<>();
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
        for(Boat b : boatRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            if(checkBoatForSearch(b,dto,rating, price) && !b.isDeleted()){
                ret.add(b);}
        }

        return ret;
    }

    private boolean checkBoatForSearch(Boat b, BoatDTO dto,double rating, double price){

        return checkStrings(b.getName(), dto.getName()) && checkStrings(b.getAddress().toString(), dto.getAddress()) && checkRating(b.getRating(), rating) && checkPrice(b.getPricePerDay(), price);
    }

    private boolean checkStrings(String boat, String search){
        if(search==null){
            return true;
        }
        return search.isEmpty() || boat.toLowerCase().contains(search.toLowerCase());
    }

    private boolean checkPrice(Double cottage, Double search){
        return cottage <= search || cottage <= 0 || search == 0;
    }

    private boolean checkRating(Double boat, Double search){
        return boat >= search || search > 5;
    }

    public AddBoatDTO addBoat(AddBoatDTO newBoat){
        Address address = new Address(newBoat.getAddress().getStreet(),newBoat.getAddress().getStreetNumber(),
                newBoat.getAddress().getPostalCode(),newBoat.getAddress().getCityName(),newBoat.getAddress().getCountryName(),
                newBoat.getAddress().getLongitude(),newBoat.getAddress().getLatitude());

        Boat boat = new Boat(newBoat.getName(),newBoat.getType(),newBoat.getLength(),newBoat.getEngineNumber(), newBoat.getEnginePower(), newBoat.getMaxSpeed(), address,
                newBoat.getDescription(),newBoat.getCapacity(), newBoat.getPricePerDay());
        boat.setCancellationConditions(newBoat.getCancellationConditions());
        User user = this.userRepository.findByEmail(newBoat.getOwnerEmail());

        for(BoatOwner b: this.boatOwnerRepository.findAll()){
            if(b.getEmail().equals(user.getEmail())){
                boat.setBoatOwner(b);
            }
        }
        boat.setCoverImage(newBoat.getCoverImage());
        this.boatRepository.save(boat);
        return newBoat;
    }


    public ResponseEntity<Long> deleteBoat(Long id){
        for(Boat b: this.boatRepository.findAll()){
            if(b.getId() == id) {
                if(b.getAppointments() != null) {
                    for(Appointment a:b.getAppointments()){
                        if(a.getReserved()){
                            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
                        }
                    }
                }
                b.setDeleted(true);
                this.boatRepository.save(b);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    public List<BoatDTO> searchClient(BoatDTO dto, String email) {
        List<BoatDTO> ret = new ArrayList<>();
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
        for(Boat b : boatRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            BoatDTO boat = new BoatDTO(b);
            boat.setIsSubscribed(clientService.checkBoatExistence(email, b.getId()));
            if(checkBoatForSearch(b,dto,rating, price) && !b.isDeleted()){
                ret.add(boat);}
        }

        return ret;
    }

    public BoatDetailDTO findBoat(Long id){
        Boat b = boatRepository.findById(id).orElseGet(null);
        BoatDetailDTO boat = new BoatDetailDTO(b);

        AddressDTO address = new AddressDTO(b.getAddress());
        boat.setAddress(address);

        List<ImageDTO> images = new ArrayList<>();
        for(Image i: b.getImages()){
            ImageDTO image = new ImageDTO(i);
            images.add(image);
        }
        boat.setImages(images);

        List<RuleDTO> rules = new ArrayList<>();
        for(Rule r: b.getRules()){
            RuleDTO rule = new RuleDTO(r);
            rules.add(rule);
        }
        boat.setRules(rules);

        List<NavigationEquipmentDTO> navigationEquipments = new ArrayList<>();
        for(NavigationEquipment n: b.getNavigationEquipments()){
            NavigationEquipmentDTO navigationEquipment = new NavigationEquipmentDTO(n);
            navigationEquipments.add(navigationEquipment);
        }
        boat.setNavigationEquipments(navigationEquipments);

        List<FishingEquipmentDTO> fishingEquipments = new ArrayList<>();
        for(FishingEquipment f: b.getFishingEquipments()){
            FishingEquipmentDTO fishingEquipment = new FishingEquipmentDTO(f);
            fishingEquipments.add(fishingEquipment);
        }
        boat.setFishingEquipments(fishingEquipments);


        return boat;
    } 

    public Boat editBasicInfo(EditBoatDTO editedBoat){
        for (Boat b: boatRepository.findAll()){
            if(editedBoat.getId().equals(b.getId())){
                b.setName(editedBoat.getName());
                b.setType(editedBoat.getType());
                b.setLength(editedBoat.getLength());
                b.setEngineNumber(editedBoat.getEngineNumber());
                b.setEnginePower(editedBoat.getEnginePower());
                b.setMaxSpeed(editedBoat.getMaxSpeed());
                b.getAddress().setCityName(editedBoat.getAddress().getCityName());
                b.getAddress().setCountryName(editedBoat.getAddress().getCountryName());
                b.getAddress().setLatitude(editedBoat.getAddress().getLatitude());
                b.getAddress().setLongitude(editedBoat.getAddress().getLongitude());
                b.getAddress().setPostalCode(editedBoat.getAddress().getPostalCode());
                b.getAddress().setStreet(editedBoat.getAddress().getStreet());
                b.getAddress().setStreetNumber(editedBoat.getAddress().getStreetNumber());
                b.setDescription(editedBoat.getDescription());
                b.setCapacity(editedBoat.getCapacity());
                b.setCancellationConditions(editedBoat.getCancellationConditions());
                b.setPricePerHour(editedBoat.getPricePerHour());
                boatRepository.save(b);
                return b;
            }
        }
        return null;
    }

    public List<BoatDTO> searchAppointments(AppointmentSearchDTO criteria){

        BoatDTO boat = new BoatDTO(criteria.getName(), criteria.getAddress(), criteria.getRating(), criteria.getPrice());
        AppointmentDTO appointment = new AppointmentDTO(criteria.getStartDate(), criteria.getEndDate(), criteria.getMaxPersons()); 


        List<Boat> boats = search(boat);
        List<BoatDTO> ret = new ArrayList<>();

        for(Boat b : boats){

            if(findAppointments(b.getAppointments(), appointment)){
                ret.add(new BoatDTO(b));
            }

        }

        return ret;
    }

    private boolean findAppointments(Set<Appointment> appointments, AppointmentDTO criteria) {

        for(Appointment a : appointments){
            if(a.getReserved() || a.isDeleted() || a.getIsAction() || (criteria.getMaxPersons()!=null && criteria.getMaxPersons() > a.getMaxPersons())){

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
