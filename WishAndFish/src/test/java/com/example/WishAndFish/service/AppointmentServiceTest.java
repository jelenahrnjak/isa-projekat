package com.example.WishAndFish.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.WishAndFish.constants.BoatsConstants;
import com.example.WishAndFish.model.Address;
import com.example.WishAndFish.model.Appointment;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.repository.AppointmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.example.WishAndFish.constants.AppointmentConstants.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @Mock
    private Appointment appointmentMock;

    @InjectMocks
    private AppointmentService appointmentService;
//
//    @Test
//    public void testFindAll() {
//
//        Boat newBoat = new Boat();
//
//        newBoat.setName("brod");
//        newBoat.setType(BoatsConstants.type);
//        newBoat.setLength(BoatsConstants.length);
//        newBoat.setEngineNumber(BoatsConstants.engineNumber);
//        newBoat.setEnginePower(BoatsConstants.enginePower);
//        newBoat.setMaxSpeed(BoatsConstants.length);
//        newBoat.setAddress(new Address("ulica","123",
//                "21000","grad","drzava",
//                62.12,12.12));
//        newBoat.setDescription(null);
//        newBoat.setCapacity(BoatsConstants.capacity);
//        newBoat.setPricePerDay(BoatsConstants.length);
//        newBoat.setMaxSpeed(BoatsConstants.length);
//        //newBoat.setBoatOwner(null);
//        newBoat.setCancellationConditions(BoatsConstants.cancellationConditions);
//        newBoat.setCoverImage(BoatsConstants.image);
//
//        when(appointmentRepositoryMock.findAll()).thenReturn(Arrays.asList(new Appointment(DB_ID, DB_START_DATE, DB_END_DATE, DB_EXPIRATION_DATE, DB_MAX_PERSONS, DB_PRICE, DB_RESERVED, DB_DELETED, DB_IS_ACTION, null,null,null)));
//
//        List<Appointment> appointments = appointmentService.findAll();
//
//        assertThat(appointments).hasSize(1);
//        assertTrue(appointments.get(0).getId() == DB_ID);
//
//        verify(appointmentRepositoryMock, times(1)).findAll();
//        verifyNoMoreInteractions(appointmentRepositoryMock);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(true) // uključeno po default-u, ne mora se navesti
//    public void testAdd() {
//
//        Appointment appointment = new Appointment(DB_ID, DB_START_DATE, DB_END_DATE, DB_EXPIRATION_DATE, DB_MAX_PERSONS, DB_PRICE, DB_RESERVED, DB_DELETED, DB_IS_ACTION, null,null,null);
//        Appointment appointment2  = new Appointment(appointment);
//        appointment2.setId(2L);
//
//        // 1. Definisanje ponašanja mock objekata
//        when(appointmentRepositoryMock.findAll()).thenReturn(Arrays.asList(appointment));
//        when(appointmentRepositoryMock.save(appointment2)).thenReturn(appointment2);
//
//        // 2. Akcija
//        int dbSizeBeforeAdd = appointmentService.findAll().size();
//
//        Appointment dbAppointment = appointmentService.save(appointment2);
//
//        when(appointmentRepositoryMock.findAll()).thenReturn(Arrays.asList(appointment, appointment2));
//
//        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
//        assertThat(dbAppointment).isNotNull();
//
//        List<Appointment> appointments = appointmentService.findAll();
//        assertThat(appointments).hasSize(dbSizeBeforeAdd + 1); //verifikacija da je novi student upisan u bazu
//
//        dbAppointment = appointments.get(appointments.size() - 1); // preuzimanje poslednjeg studenta
//
//        assertThat(dbAppointment.getId() == 2L);
//
//        verify(appointmentRepositoryMock, times(2)).findAll();
//        verify(appointmentRepositoryMock, times(1)).save(appointment2);
//        verifyNoMoreInteractions(appointmentRepositoryMock);
//    }
//
//    @Test
//    public void testFindOne() {
//        // 1. Definisanje ponašanja mock objekata
//        when(appointmentRepositoryMock.findById(DB_ID)).thenReturn(Optional.of(appointmentMock));
//
//        // 2. Akcija
//        Appointment dbAppointment = appointmentService.findOne(DB_ID);
//
//        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
//        assertEquals(appointmentMock, dbAppointment);
//
//        verify(appointmentRepositoryMock, times(1)).findById(DB_ID);
//        verifyNoMoreInteractions(appointmentRepositoryMock);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void testRemove() {
//
//        Appointment appointment = new Appointment(DB_ID, DB_START_DATE, DB_END_DATE, DB_EXPIRATION_DATE, DB_MAX_PERSONS, DB_PRICE, DB_RESERVED, DB_DELETED, DB_IS_ACTION, null,null,null);
//        Appointment appointmentToDelete  = new Appointment(appointment);
//        appointmentToDelete.setId(DB_ID_TO_DELETE);
//
//        // 1. Definisanje ponašanja mock objekata
//        when(appointmentRepositoryMock.findAll()).thenReturn(Arrays.asList(appointment, appointmentToDelete));
//        doNothing().when(appointmentRepositoryMock).deleteById(DB_ID_TO_DELETE);
//
//        when(appointmentRepositoryMock.findById(DB_ID_TO_DELETE)).thenReturn(Optional.empty());
//
//        // 2. Akcija
//        int dbSizeBeforeRemove = appointmentService.findAll().size();
//        appointmentService.remove(DB_ID_TO_DELETE);
//
//        when(appointmentRepositoryMock.findAll()).thenReturn(Arrays.asList(appointment));
//
//        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
//        List<Appointment> appointments = appointmentService.findAll();
//        assertThat(appointments).hasSize(dbSizeBeforeRemove - 1);
//
//        Appointment dbAppointment = appointmentService.findOne(DB_ID_TO_DELETE);
//        assertThat(dbAppointment).isNull();
//
//        verify(appointmentRepositoryMock, times(1)).deleteById(DB_ID_TO_DELETE);
//        verify(appointmentRepositoryMock, times(2)).findAll();
//        verify(appointmentRepositoryMock, times(1)).findById(DB_ID_TO_DELETE);
//        verifyNoMoreInteractions(appointmentRepositoryMock);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void testUpdate() {
//
//        Appointment appointment = new Appointment(DB_ID, DB_START_DATE, DB_END_DATE, DB_EXPIRATION_DATE, DB_MAX_PERSONS, DB_PRICE, DB_RESERVED, DB_DELETED, DB_IS_ACTION, null,null,null);
//
//
//        // 1. Definisanje ponašanja mock objekata
//        when(appointmentRepositoryMock.findById(DB_ID)).thenReturn(Optional.of(appointment));
//
//        // 2. Akcija
//        Appointment appointmentForUpdate = appointmentService.findOne(DB_ID);
//        appointmentForUpdate.setId(DB_NEW_ID);
//
//        when(appointmentRepositoryMock.save(appointmentForUpdate)).thenReturn(appointmentForUpdate);
//
//        appointmentForUpdate = appointmentService.save(appointmentForUpdate);
//
//        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
//        assertThat(appointmentForUpdate).isNotNull();
//
//        appointmentForUpdate = appointmentService.findOne(DB_ID); // verifikacija da se u bazi nalaze izmenjeni podaci
//        assertThat(appointmentForUpdate.getId() == DB_NEW_ID);
//
//        verify(appointmentRepositoryMock, times(2)).findById(DB_ID);
//        verify(appointmentRepositoryMock, times(1)).save(appointmentForUpdate);
//        verifyNoMoreInteractions(appointmentRepositoryMock);
//    }
}
