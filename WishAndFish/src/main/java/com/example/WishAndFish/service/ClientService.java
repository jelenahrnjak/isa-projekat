package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.BookingHistoryDTO;
import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.dto.FishingAdventureDTO;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.ClientRepository;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.FishingAdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Comparator.comparing;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private FishingAdventureRepository fishingAdventureRepository;

    public boolean subscribeToCottage(Long cottageId, String userEmail) {
        Client client = clientRepository.findByEmail(userEmail);
        Cottage cottage = cottageRepository.getById(cottageId);

        if (checkCottageExistence(userEmail, cottageId)) {
            return false;
        }

        addCottageToClientsSubscriptions(client, cottage);
        return true;
    }

    public boolean unsubscribeFromCottage(Long cottageId, String userEmail) {
        Client client = clientRepository.findByEmail(userEmail);
        Cottage cottage = cottageRepository.getById(cottageId);

        if (!checkCottageExistence(userEmail, cottageId)) {
            return false;
        }

        removeCottageFromClientsSubscriptions(client, cottage);
        return true;
    }

    private void removeCottageFromClientsSubscriptions(Client client, Cottage cottage) {

        for(Cottage c : client.getCottageSubscriptions()){
            if(c.getId() == cottage.getId()){
                client.getCottageSubscriptions().remove(c);
                clientRepository.save(client);
                return;
            }
        }
    }

    public boolean checkCottageExistence(String userEmail, Long cottageId) {

        Client client = clientRepository.findByEmail(userEmail);

        for (Cottage c : client.getCottageSubscriptions()) {
            if (c.getId() == cottageId) {
                return true;
            }
        }

        return false;
    }

    private void addCottageToClientsSubscriptions(Client client, Cottage cottage) {
        client.getCottageSubscriptions().add(cottage);
        clientRepository.save(client);
    }

    public boolean subscribeToBoat(Long boatId, String userEmail) {
        Client client = clientRepository.findByEmail(userEmail);
        Boat boat = boatRepository.getById(boatId);

        if (checkBoatExistence(userEmail, boatId)) {
            return false;
        }

        addBoatToClientsSubscriptions(client, boat);
        return true;
    }


    public boolean unsubscribeFromBoat(Long boatId, String userEmail) {
        Client client = clientRepository.findByEmail(userEmail);
        Boat boat = boatRepository.getById(boatId);

        if (!checkBoatExistence(userEmail, boatId)) {
            return false;
        }

        removeBoatFromClientsSubscriptions(client, boat);
        return true;
    }

    private void removeBoatFromClientsSubscriptions(Client client, Boat boat) {

        for(Boat c : client.getBoatSubscriptions()){
            if(c.getId() == boat.getId()){
                client.getBoatSubscriptions().remove(c);
                clientRepository.save(client);
                return;
            }
        }
    }

    public boolean checkBoatExistence(String userEmail, Long boatId) {

        Client client = clientRepository.findByEmail(userEmail);

        for (Boat c : client.getBoatSubscriptions()) {
            if (c.getId() == boatId) {
                return true;
            }
        }

        return false;
    }


    private void addBoatToClientsSubscriptions(Client client, Boat boat) {
        client.getBoatSubscriptions().add(boat);
        clientRepository.save(client);
    }

    public boolean subscribeToAdventure(Long adventureId, String userEmail) {
        Client client = clientRepository.findByEmail(userEmail);
        FishingAdventure adventure = fishingAdventureRepository.getById(adventureId);

        if (checkAdventureExistence(userEmail, adventureId)) {
            return false;
        }

        addAdventureToClientsSubscriptions(client, adventure);
        return true;
    }
    public boolean unsubscribeFromAdventure(Long adventureId, String userEmail) {
        Client client = clientRepository.findByEmail(userEmail);
        FishingAdventure adventure = fishingAdventureRepository.getById(adventureId);

        if (!checkAdventureExistence(userEmail, adventureId)) {
            return false;
        }

        removeAdventureFromClientsSubscriptions(client, adventure);
        return true;
    }

    private void removeAdventureFromClientsSubscriptions(Client client, FishingAdventure adventure) {

        for(FishingAdventure c : client.getAdventureSubscriptions()){
            if(c.getId() == adventure.getId()){
                client.getAdventureSubscriptions().remove(c);
                clientRepository.save(client);
                return;
            }
        }
    }
    public boolean checkAdventureExistence(String userEmail, Long adventureId) {

        Client client = clientRepository.findByEmail(userEmail);

        for (FishingAdventure c : client.getAdventureSubscriptions()) {
            if (c.getId() == adventureId) {
                return true;
            }
        }

        return false;
    }

    private void addAdventureToClientsSubscriptions(Client client, FishingAdventure adventure) {
        client.getAdventureSubscriptions().add(adventure);
        clientRepository.save(client);
    }

    public List<CottageDTO> getAllCottagesSubscriptions(String email) {

        List<CottageDTO> ret = new ArrayList<>();

        for(Cottage c : clientRepository.findByEmail(email).getCottageSubscriptions()){
            if(!c.isDeleted()){
                ret.add(new CottageDTO(c));}

        };

        return ret;
    }

    public List<BoatDTO> getAllBoatsSubscriptions(String email) {

        List<BoatDTO> ret = new ArrayList<>();

        for(Boat b : clientRepository.findByEmail(email).getBoatSubscriptions()){
            if(!b.isDeleted()){
                ret.add(new BoatDTO(b));}

        };

        return ret;
    }

    public List<FishingAdventureDTO> getAllAdventuresSubscriptions(String email) {

        List<FishingAdventureDTO> ret = new ArrayList<>();

        for(FishingAdventure a : clientRepository.findByEmail(email).getAdventureSubscriptions()){

            if(!a.isDeleted()){
                ret.add(new FishingAdventureDTO(a));}

        };

        return ret;

    }

    public Client addPenaltyToClient(String email) {
        Client client = clientRepository.findByEmail(email);
        if(client == null){
            return null;
        }
        client.setPenalties(client.getPenalties() + 1);
        return clientRepository.save(client);
    }

    public Integer getPenalties(String email) {

        Client client = clientRepository.findByEmail(email);
        if(client == null){
            return -1;
        }

        return client.getPenalties();
    }
}
