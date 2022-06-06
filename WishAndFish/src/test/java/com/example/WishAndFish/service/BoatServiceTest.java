package com.example.WishAndFish.service;
import com.example.WishAndFish.constants.AddressConstants;
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
        forUpdate.setId(BoatsConstants.id);
        forUpdate.setName(BoatsConstants.name);
        forUpdate.setType(BoatsConstants.type);
        forUpdate.setLength(BoatsConstants.length);
        forUpdate.setAddress(new Address (AddressConstants.street,AddressConstants.streetNumber,
                AddressConstants.postalCode,AddressConstants.city,AddressConstants.country,
                AddressConstants.lng,AddressConstants.ltd));

        when(boatRepositoryMock.findOneById(1L)).thenReturn(forUpdate);
        when(reservationRepositoryMock.findAll()).thenReturn(new ArrayList<>());


        Boat updated = new Boat();
        updated.setId(1L);
        updated.setName(BoatsConstants.name2);
        updated.setType(BoatsConstants.type2);
        updated.setLength(BoatsConstants.length2);
        updated.setAddress(new Address (AddressConstants.street,AddressConstants.streetNumber,
                AddressConstants.postalCode,AddressConstants.city,AddressConstants.country,
                AddressConstants.lng,AddressConstants.ltd));

        EditBoatDTO edit = new EditBoatDTO();
        edit.setId(BoatsConstants.id);
        edit.setName(BoatsConstants.name2);
        edit.setType(BoatsConstants.type2);
        edit.setLength(BoatsConstants.length2);
        edit.setAddress(new AddressDTO(new Address (AddressConstants.street,AddressConstants.streetNumber,
                AddressConstants.postalCode,AddressConstants.city,AddressConstants.country,
                AddressConstants.lng,AddressConstants.ltd)));
        // 2. Akcija
        Boat edited = boatService.editBasicInfo(edit);


        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(edited).isNotNull();

        when(boatRepositoryMock.findById(1L)).thenReturn(Optional.of(edited));
        BoatDetailDTO found = boatService.findBoat(BoatsConstants.id); // verifikacija da se u bazi nalaze izmenjeni podaci

        assertThat(found.getName()).isEqualTo(updated.getName());
        assertThat(found.getType()).isEqualTo(updated.getType());
        assertThat(found.getLength()).isEqualTo(updated.getLength());

        verify(boatRepositoryMock, times(1)).findById(BoatsConstants.id);
        verify(boatRepositoryMock, times(1)).findOneById(BoatsConstants.id);
        verify(reservationRepositoryMock, times(1)).findAll();
        verify(boatRepositoryMock, times(1)).save(edited);
        verifyNoMoreInteractions(boatRepositoryMock);
    }


    @Test
    public void testGetAllFromOwner() {
        // 1. Definisanje ponašanja mock objekata (ja mu kazem kada se pozove metoda mock-a, da mi vrati tu konkretnu kucu (definisem ponasanje metode))
        Boat b1 = new Boat();
        b1.setId(1L);
        b1.setName(BoatsConstants.name3);
        User u = new User();
        u.setId(BoatsConstants.id4);
        u.setEmail(BoatsConstants.ownerEmail);
        b1.setBoatOwner(new BoatOwner(u));
        b1.setAddress(new Address (AddressConstants.street,AddressConstants.streetNumber,
                AddressConstants.postalCode,AddressConstants.city,AddressConstants.country,
                AddressConstants.lng,AddressConstants.ltd));
        b1.setDeleted(false);

        Boat b2 = new Boat();
        b2.setId(2L);
        b2.setName(BoatsConstants.name4);
        User u2 = new User();
        u2.setId(BoatsConstants.id5);
        u2.setEmail(BoatsConstants.ownerEmail2);
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
