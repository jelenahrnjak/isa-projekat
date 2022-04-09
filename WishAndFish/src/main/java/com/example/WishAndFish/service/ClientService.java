package com.example.WishAndFish.service;

import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Client;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.FishingAdventure;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.ClientRepository;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.FishingAdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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

        if(checkCottageExistence(userEmail,cottageId)){
            return false;
        }

        addCottageToClientsSubscriptions(client, cottage);
        return true;
    }

    public boolean checkCottageExistence(String userEmail, Long cottageId){

        Client client = clientRepository.findByEmail(userEmail);

        for(Cottage c : client.getCottageSubscriptions()){
            if(c.getId() == cottageId){
                return true;
            }
        }

        return false;
    }

    private void addCottageToClientsSubscriptions(Client client, Cottage cottage) {
        Set<Cottage> subscriptions = client.getCottageSubscriptions();
        subscriptions.add(cottage);
        client.setCottageSubscriptions(subscriptions);
        clientRepository.save(client);
    }

    public boolean subscribeToBoat(Long boatId, String userEmail) {
        Client client = clientRepository.findByEmail(userEmail);
        Boat boat = boatRepository.getById(boatId);

        if(checkBoatExistence(userEmail,boatId)){
            return false;
        }

        addBoatToClientsSubscriptions(client, boat);
        return true;
    }

    public boolean checkBoatExistence(String userEmail, Long boatId){

        Client client = clientRepository.findByEmail(userEmail);

        for(Boat c : client.getBoatSubscriptions()){
            if(c.getId() == boatId){
                return true;
            }
        }

        return false;
    }

    private void addBoatToClientsSubscriptions(Client client, Boat boat) {
        Set<Boat> subscriptions = client.getBoatSubscriptions();
        subscriptions.add(boat);
        client.setBoatSubscriptions(subscriptions);
        clientRepository.save(client);
    }

    public boolean subscribeToAdventure(Long adventureId, String userEmail) {
        Client client = clientRepository.findByEmail(userEmail);
        FishingAdventure adventure = fishingAdventureRepository.getById(adventureId);

        if(checkAdventureExistence(userEmail,adventureId)){
            return false;
        }

        addAdventureToClientsSubscriptions(client, adventure);
        return true;
    }

    public boolean checkAdventureExistence(String userEmail, Long adventureId){

        Client client = clientRepository.findByEmail(userEmail);
        
        for(FishingAdventure c : client.getAdventureSubscriptions()){
            if(c.getId() == adventureId){
                return true;
            }
        }

        return false;
    }

    private void addAdventureToClientsSubscriptions(Client client, FishingAdventure adventure) {
        Set<FishingAdventure> subscriptions = client.getAdventureSubscriptions();
        subscriptions.add(adventure);
        client.setAdventureSubscriptions(subscriptions);
        clientRepository.save(client);
    }

}
