package com.example.WishAndFish.service;
import com.example.WishAndFish.constants.BoatsConstants;
import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.BoatOwnerRepository;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.ReservationRepository;
import com.example.WishAndFish.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    private UserRepository userRepositoryMock;

    @Mock
    private BoatOwnerRepository boatOwnerRepositoryMock;

    @Mock
    private Boat boatMock;

    @InjectMocks
    private BoatService boatService;

    @InjectMocks
    private BoatOwnerService boatOwnerService;


    @Test
    @Transactional
    public void testUpdate() {
        // 1. Definisanje ponašanja mock objekata
        Boat forUpdate = new Boat();
        forUpdate.setId(1L);
        forUpdate.setName("The best boat");
        forUpdate.setType("Fishing");
        forUpdate.setLength(15.5);
        forUpdate.setAddress(new Address ("ulica","123",
                "21000","grad","drzava",
                62.12,12.12));

        when(boatRepositoryMock.findOneById(1L)).thenReturn(forUpdate);
        when(reservationRepositoryMock.findAll()).thenReturn(new ArrayList<>());


        Boat updated = new Boat();
        updated.setId(1L);
        updated.setName("The best");
        updated.setType("Cruiser");
        updated.setLength(17.0);
        updated.setAddress(new Address ("ulica","123",
                "21000","grad","drzava",
                62.12,12.12));

        EditBoatDTO edit = new EditBoatDTO();
        edit.setId(1L);
        edit.setName("The best");
        edit.setType("Cruiser");
        edit.setLength(17.0);
        edit.setAddress(new AddressDTO(new Address ("ulica","123",
                "21000","grad","drzava",
                62.12,12.12)));
        // 2. Akcija
        Boat edited = boatService.editBasicInfo(edit);


        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(edited).isNotNull();

        when(boatRepositoryMock.findById(1L)).thenReturn(Optional.of(edited));
        BoatDetailDTO found = boatService.findBoat(1L); // verifikacija da se u bazi nalaze izmenjeni podaci

        assertThat(found.getName()).isEqualTo(updated.getName());
        assertThat(found.getType()).isEqualTo(updated.getType());
        assertThat(found.getLength()).isEqualTo(updated.getLength());

        verify(boatRepositoryMock, times(1)).findById(1L);
        verify(boatRepositoryMock, times(1)).findOneById(1L);
        verify(reservationRepositoryMock, times(1)).findAll();
        verify(boatRepositoryMock, times(1)).save(edited);
        verifyNoMoreInteractions(boatRepositoryMock);
    }


    @Test
    public void testGetAllFromOwner() {
        // 1. Definisanje ponašanja mock objekata (ja mu kazem kada se pozove metoda mock-a, da mi vrati tu konkretnu kucu (definisem ponasanje metode))
        Boat b1 = new Boat();
        b1.setId(1L);
        b1.setName("yachta");
        User u = new User();
        u.setId(2L);
        u.setEmail("markomarko@gmail.com");
        b1.setBoatOwner(new BoatOwner(u));
        b1.setAddress(new Address ("ulica","123",
                "21000","grad","drzava",
                62.12,12.12));
        b1.setDeleted(false);

        Boat b2 = new Boat();
        b2.setId(2L);
        b2.setName("yachta2");
        User u2 = new User();
        u2.setId(22L);
        u2.setEmail("owner@gmail.com");
        b2.setBoatOwner(new BoatOwner(u2));
        b2.setDeleted(false);


        when(boatRepositoryMock.findAll()).thenReturn(Arrays.asList(b1, b2));
        when(userRepositoryMock.findByEmail(u.getEmail())).thenReturn(u);

        // 2. Akcija
        List<BoadDisplayDTO> boats = boatOwnerService.getBoatsFromOwner(u.getEmail());

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(boats).hasSize(1);
        assertEquals(1L, (long) boats.get(0).getId());

		/*
		Možemo verifikovati ponašanje mokovanih objekata pozivanjem verify* metoda.
		 */
        verify(boatRepositoryMock, times(1)).findAll();
        verify(userRepositoryMock, times(1)).findByEmail(u.getEmail());

        verifyNoMoreInteractions(boatRepositoryMock);
    }


}
