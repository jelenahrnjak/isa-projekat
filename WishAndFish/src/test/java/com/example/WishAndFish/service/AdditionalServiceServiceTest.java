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
public class AdditionalServiceServiceTest {

    @Mock
    private BoatRepository boatRepositoryMock;

    @Mock
    private ReservationRepository reservationRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private AdditionalServiceRepository additionalServiceRepositoryMock;

    @InjectMocks
    private AdditionalServiceService additionalServiceService;


    @Test
    public void testGetAllForAppointment() {
        // 1. Definisanje ponašanja mock objekata
        AdditionalService a1 = new AdditionalService();
        a1.setId(1L);
        a1.setDeleted(false);
        a1.setName("Wi-Fi");
        Appointment ap1 = new Appointment();
        ap1.setId(1L);
        Appointment ap2 = new Appointment();
        ap2.setId(2L);
        a1.getAppointments().add(ap1);
        a1.getAppointments().add(ap2);


        AdditionalService a2 = new AdditionalService();
        a2.setId(4L);
        a2.setDeleted(true);
        a2.setName("Fruit");
        a2.getAppointments().add(ap1);

        when(additionalServiceRepositoryMock.findAll()).thenReturn(Arrays.asList(a1, a2));

        // 2. Akcija
        List<AdditionalServicesDTO> addServices = additionalServiceService.findAdditionalServicesForAppointment(ap1.getId());

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(addServices).hasSize(1);

		/*
		Možemo verifikovati ponašanje mokovanih objekata pozivanjem verify* metoda.
		 */
        verify(additionalServiceRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(boatRepositoryMock);
    }
}
