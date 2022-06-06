package com.example.WishAndFish.service;
import com.example.WishAndFish.constants.BoatsConstants;
import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.Address;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Reservation;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoatServiceTest {

    @Mock
    private BoatRepository boatRepositoryMock;

    @Mock
    private ReservationRepository reservationRepositoryMock;

    @Mock
    private Boat boatMock;

    @InjectMocks
    private BoatService boatService;


    @Test
    public void testGetBoatById(){
        // 1. Definisanje ponašanja mock objekata
        when(boatRepositoryMock.getById(BoatsConstants.id)).thenReturn(boatMock);
        System.out.println(BoatsConstants.id);
        // 2. Akcija
        BoatDetailDTO dbBoat = boatService.findBoat(123L);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertEquals(boatMock, dbBoat);

        verify(boatRepositoryMock, times(1)).getById(BoatsConstants.id);
        verifyNoMoreInteractions(boatRepositoryMock);
    }


    @Test
    public void testGetAll() {
        // 1. Definisanje ponašanja mock objekata (ja mu kazem kada se pozove metoda mock-a, da mi vrati tu konkretnu kucu (definisem ponasanje metode))
        when(boatRepositoryMock.findAll()).thenReturn(Arrays.asList(new Boat()));

        // 2. Akcija
        List<BoatDTO> boats = boatService.findAll();

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(boats).hasSize(3);

		/*
		Možemo verifikovati ponašanje mokovanih objekata pozivanjem verify* metoda.
		 */
        verify(boatRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(boatRepositoryMock);
    }


    @Test
    @Transactional
    @Rollback(true) // uključeno po default-u, ne mora se navesti
    public void testAdd() {
        AddBoatDTO boat = new AddBoatDTO();

        boat.setName(BoatsConstants.name);
        boat.setType(BoatsConstants.type);
        boat.setLength(BoatsConstants.length);
        boat.setEngineNumber(BoatsConstants.engineNumber);
        boat.setEnginePower(BoatsConstants.enginePower);
        boat.setMaxSpeed(BoatsConstants.length);
        boat.setAddress(new Address ("ulica","123",
                "21000","grad","drzava",
                62.12,12.12));
        boat.setDescription(null);
        boat.setCapacity(BoatsConstants.capacity);
        boat.setPricePerDay(BoatsConstants.length);
        boat.setMaxSpeed(BoatsConstants.length);
        boat.setOwnerEmail(BoatsConstants.ownerEmail);
        boat.setCancellationConditions(BoatsConstants.cancellationConditions);
        boat.setCoverImage(BoatsConstants.image);



        Boat newBoat = new Boat();

        newBoat.setName("brod");
        newBoat.setType(BoatsConstants.type);
        newBoat.setLength(BoatsConstants.length);
        newBoat.setEngineNumber(BoatsConstants.engineNumber);
        newBoat.setEnginePower(BoatsConstants.enginePower);
        newBoat.setMaxSpeed(BoatsConstants.length);
        newBoat.setAddress(new Address ("ulica","123",
                "21000","grad","drzava",
                62.12,12.12));
        newBoat.setDescription(null);
        newBoat.setCapacity(BoatsConstants.capacity);
        newBoat.setPricePerDay(BoatsConstants.length);
        newBoat.setMaxSpeed(BoatsConstants.length);
        //newBoat.setBoatOwner(null);
        newBoat.setCancellationConditions(BoatsConstants.cancellationConditions);
        newBoat.setCoverImage(BoatsConstants.image);


        //Boat b = new Boat();
        // 1. Definisanje ponašanja mock objekata
        when(boatRepositoryMock.findAll()).thenReturn(Arrays.asList(new Boat()));
        when(boatRepositoryMock.save(newBoat)).thenReturn(newBoat);

        // 2. Akcija
        int dbSizeBeforeAdd = boatService.findAll().size();

        AddBoatDTO dbBoat = boatService.addBoat(boat);

        when(boatRepositoryMock.findAll()).thenReturn(Arrays.asList(new Boat(), newBoat));

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(dbBoat).isNotNull();

        List<BoatDTO> boats = boatService.findAll();
        assertThat(boats).hasSize(dbSizeBeforeAdd + 1); //verifikacija da je novi brod upisan u bazu

        BoatDTO b2 = new BoatDTO();
        b2 = boats.get(boats.size() - 1); // preuzimanje poslednjeg broda

        assertThat(dbBoat.getName()).isEqualToIgnoringCase(BoatsConstants.name);
        assertThat(dbBoat.getType()).isEqualTo(BoatsConstants.type);
        assertThat(dbBoat.getLength()).isEqualTo(BoatsConstants.length);
        assertThat(dbBoat.getEngineNumber()).isEqualTo(BoatsConstants.engineNumber);
        assertThat(dbBoat.getEnginePower()).isEqualTo(BoatsConstants.enginePower);
        assertThat(dbBoat.getMaxSpeed()).isEqualTo(BoatsConstants.length);
        assertThat(dbBoat.getAddress()).isEqualTo(null);
        assertThat(dbBoat.getDescription()).isEqualTo(BoatsConstants.description);
        assertThat(dbBoat.getCapacity()).isEqualTo(BoatsConstants.capacity);
        assertThat(dbBoat.getLength()).isEqualTo(BoatsConstants.length);
        assertThat(dbBoat.getMaxSpeed()).isEqualTo(BoatsConstants.maxSpeed);
        assertThat(dbBoat.getOwnerEmail()).isEqualTo(BoatsConstants.ownerEmail);
        assertThat(dbBoat.getCancellationConditions()).isEqualTo(BoatsConstants.cancellationConditions);
        assertThat(dbBoat.getCoverImage()).isEqualTo(BoatsConstants.image);

        verify(boatRepositoryMock, times(2)).findAll();
        verify(boatRepositoryMock, times(1)).save(newBoat);
        verifyNoMoreInteractions(boatRepositoryMock);
    }


    @Test
    @Transactional
    public void testUpdate() {
        // 1. Definisanje ponašanja mock objekata
        when(boatRepositoryMock.findOneById(BoatsConstants.id)).thenReturn(new Boat());

        Reservation r = new Reservation();
        when(reservationRepositoryMock.findAll()).thenReturn(Arrays.asList(new Reservation(), r));

        Boat b= new Boat();
        when(boatRepositoryMock.save(b)).thenReturn(b);

        EditBoatDTO boat = new EditBoatDTO();
        boat.setId(125L);
        boat.setName(BoatsConstants.name);
        boat.setType(BoatsConstants.type);
        boat.setLength(BoatsConstants.length);
        boat.setEngineNumber(BoatsConstants.engineNumber);
        boat.setEnginePower(BoatsConstants.enginePower);
        boat.setMaxSpeed(BoatsConstants.length);
        boat.setAddress(new AddressDTO("ulica","123",
                "21000", 15, 16, "grad","drzava"));
        boat.setDescription(BoatsConstants.description);
        boat.setCapacity(BoatsConstants.capacity);
        boat.setCancellationConditions(BoatsConstants.cancellationConditions);
        boat.setPricePerHour(BoatsConstants.pricePerDay);


        // 2. Akcija
        Boat boatForUpdate = boatService.editBasicInfo(boat);


        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(boatForUpdate).isNotNull();

        BoatDetailDTO edited = boatService.findBoat(125l); // verifikacija da se u bazi nalaze izmenjeni podaci
        assertThat(edited.getName()).isEqualTo(BoatsConstants.name);
        assertThat(edited.getType()).isEqualTo(BoatsConstants.type);

        verify(boatRepositoryMock, times(2)).getById(125L);
        verify(boatRepositoryMock, times(1)).save(boatForUpdate);
        verifyNoMoreInteractions(boatRepositoryMock);
    }

}
