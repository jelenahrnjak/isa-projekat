package com.example.WishAndFish.service;

import com.example.WishAndFish.constants.BoatsConstants;
import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.*;
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
public class CottageServiceTest {
    @Mock
    private CottageRepository cottageRepositoryMock;

    @Mock
    private ReservationRepository reservationRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private CottageOwnerRepository cottageOwnerRepository;

    @Mock
    private Cottage cottageMock;

    @InjectMocks
    private CottageService cottageService;

    @InjectMocks
    private CottageOwnerService cottageOwnerService;

    @Test
    public void testGetAllFromOwner() {
        // 1. Definisanje ponašanja mock objekata
        Cottage c1 = new Cottage();
        c1.setId(1L);
        c1.setName("yachta");
        User u = new User();
        u.setId(2L);
        u.setEmail("stojic.kris@gmail.com");
        c1.setCottageOwner(new CottageOwner(u));
        c1.setAddress(new Address("ulica","123",
                "21000","grad","drzava",
                62.12,12.12));
        c1.setDeleted(false);

        Cottage c2 = new Cottage();
        c2.setId(2L);
        c2.setName("yachta2");
        User u2 = new User();
        u2.setId(22L);
        u2.setEmail("owner@gmail.com");
        c2.setCottageOwner(new CottageOwner(u2));
        c2.setDeleted(false);


        when(cottageRepositoryMock.findAll()).thenReturn(Arrays.asList(c1, c2));
        when(userRepositoryMock.findByEmail(u.getEmail())).thenReturn(u);

        // 2. Akcija
        List<CottageDisplayDTO> cottages = cottageOwnerService.getCottagesFromOwner(u.getEmail());

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(cottages).hasSize(1);
        assertEquals(1L, (long) cottages.get(0).getId());

		/*
		Možemo verifikovati ponašanje mokovanih objekata pozivanjem verify* metoda.
		 */
        verify(cottageRepositoryMock, times(1)).findAll();
        verify(userRepositoryMock, times(1)).findByEmail(u.getEmail());

        verifyNoMoreInteractions(cottageRepositoryMock);
    }


    @Test
    public void getByIdTest() {
        Cottage c = new Cottage();
        c.setId(1L);
        c.setName("House");
        c.setBedsPerRoom(5);
        c.setNumberOfRooms(4);
        when(cottageRepositoryMock.findById(1L)).thenReturn(Optional.of(cottageMock));

        Cottage cottage = cottageService.findCottage(1L);

        assertEquals(cottage, cottageMock);
        verify(cottageRepositoryMock, times(1)).findById(1L);
        verifyNoMoreInteractions(cottageRepositoryMock);
    }


}
