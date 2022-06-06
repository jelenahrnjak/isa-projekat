package com.example.WishAndFish.service;

import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.ClientRepository;
import com.example.WishAndFish.repository.CottageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static com.example.WishAndFish.constants.ClientConstants.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    @Mock
    private CottageRepository cottageRepositoryMock;

    @Mock
    private Client clientMock;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testIsSubscribedToBoat(){

        User user = new User(DB_PASS, DB_EMAIL, DB_NAME, DB_SURNAME, DB_PHONE);
        user.setId(DB_ID);

        Client client = new Client(user);

        Boat boat = new Boat();
        boat.setId(DB_BOAT_ID);

        Boat boat2 = new Boat();
        boat2.setId(DB_BOAT_ID2);

        client.getBoatSubscriptions().add(boat);
        client.getBoatSubscriptions().add(boat2);

        when(clientRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(client);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertTrue(clientService.checkBoatExistence(DB_EMAIL, DB_BOAT_ID));
        assertTrue(clientService.checkBoatExistence(DB_EMAIL, DB_BOAT_ID2));

        verify(clientRepositoryMock, times(2)).findByEmail(DB_EMAIL);
        verifyNoMoreInteractions(clientRepositoryMock);
    }

    @Test
    public void testIsSubscribedToCottage(){

        User user = new User(DB_PASS, DB_EMAIL, DB_NAME, DB_SURNAME, DB_PHONE);
        user.setId(DB_ID);

        Client client = new Client(user);

        Cottage cottage = new Cottage();
        cottage.setId(DB_COTTAGE_ID);

        Cottage cottage2 = new Cottage();
        cottage2.setId(DB_COTTAGE_ID2);

        client.getCottageSubscriptions().add(cottage);
        client.getCottageSubscriptions().add(cottage2);

        when(clientRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(client);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertTrue(clientService.checkCottageExistence(DB_EMAIL, DB_COTTAGE_ID));
        assertTrue(clientService.checkCottageExistence(DB_EMAIL, DB_COTTAGE_ID2));

        verify(clientRepositoryMock, times(2)).findByEmail(DB_EMAIL);
        verifyNoMoreInteractions(clientRepositoryMock);
    }

    @Test
    public void testIsSubscribedToAdventure(){

        User user = new User(DB_PASS, DB_EMAIL, DB_NAME, DB_SURNAME, DB_PHONE);
        user.setId(DB_ID);

        Client client = new Client(user);

        FishingAdventure adventure = new FishingAdventure();
        adventure.setId(DB_ADVENTURE_ID);
        FishingAdventure adventure2 = new FishingAdventure();
        adventure2.setId(DB_ADVENTURE_ID2);

        client.getAdventureSubscriptions().add(adventure);
        client.getAdventureSubscriptions().add(adventure2);

        when(clientRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(client);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertTrue(clientService.checkAdventureExistence(DB_EMAIL, DB_ADVENTURE_ID2));
        assertTrue(clientService.checkAdventureExistence(DB_EMAIL, DB_ADVENTURE_ID));

        verify(clientRepositoryMock, times(2)).findByEmail(DB_EMAIL);
        verifyNoMoreInteractions(clientRepositoryMock);
    }


    @Test
    public void testUnsubscribeToCottage(){

        User user = new User(DB_PASS, DB_EMAIL, DB_NAME, DB_SURNAME, DB_PHONE);
        user.setId(DB_ID);

        Client client = new Client(user);

        Cottage cottage = new Cottage();
        cottage.setId(DB_COTTAGE_ID);

        Cottage cottage2 = new Cottage();
        cottage2.setId(DB_COTTAGE_ID2);

        client.getCottageSubscriptions().add(cottage);
        client.getCottageSubscriptions().add(cottage2);

        when(cottageRepositoryMock.getById(DB_COTTAGE_ID)).thenReturn(cottage);
        when(clientRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(client);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertTrue(clientService.unsubscribeFromCottage(DB_COTTAGE_ID, DB_EMAIL));
        assertFalse(clientService.checkCottageExistence(DB_EMAIL, DB_COTTAGE_ID));

        verify(clientRepositoryMock, times(3)).findByEmail(DB_EMAIL);
        verify(cottageRepositoryMock, times(1)).getById(DB_COTTAGE_ID);
        verifyNoMoreInteractions(cottageRepositoryMock);
    }

    @Test
    public void testSubscribeToCottage(){

        User user = new User(DB_PASS, DB_EMAIL, DB_NAME, DB_SURNAME, DB_PHONE);
        user.setId(DB_ID);

        Client client = new Client(user);

        Cottage cottage = new Cottage();
        cottage.setId(DB_COTTAGE_ID);

        Cottage cottage2 = new Cottage();
        cottage2.setId(DB_COTTAGE_ID2);

        client.getCottageSubscriptions().add(cottage);

        when(cottageRepositoryMock.getById(DB_COTTAGE_ID2)).thenReturn(cottage2);
        when(clientRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(client);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertTrue(clientService.subscribeToCottage(DB_COTTAGE_ID2, DB_EMAIL));
        assertTrue(clientService.checkCottageExistence(DB_EMAIL, DB_COTTAGE_ID2));

        verify(clientRepositoryMock, times(3)).findByEmail(DB_EMAIL);
        verify(cottageRepositoryMock, times(1)).getById(DB_COTTAGE_ID2);
        verifyNoMoreInteractions(cottageRepositoryMock);
    }
}


