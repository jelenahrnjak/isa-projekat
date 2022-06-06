package com.example.WishAndFish.service;

import com.example.WishAndFish.constants.AppointmentConstants;
import com.example.WishAndFish.constants.ClientConstants;
import com.example.WishAndFish.constants.FishingAdventureConstants;
import com.example.WishAndFish.dto.CommentDTO;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.WishAndFish.constants.AppointmentConstants.*;
import static com.example.WishAndFish.constants.FishingAdventureConstants.DB_ID;
import static com.example.WishAndFish.constants.FishingAdventureConstants.DB_NAME;
import static com.example.WishAndFish.constants.FishingAdventureConstants.DB_PRICE;
import static com.example.WishAndFish.constants.ReservationConstants.*;
import static com.example.WishAndFish.constants.ClientConstants.*;
import static com.example.WishAndFish.constants.FishingAdventureConstants.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

    @Mock
    private Reservation reservationMock;

    @Mock
    private ReservationRepository reservationRepositoryMock;

    @Mock
    private ComplaintRepository complaintRepositoryMock;

    @Mock
    private ReviewRepository reviewRepositoryMock;

    @Mock
    private FishingAdventureRepository fishingAdventureRepositoryMock;

    @Mock
    private ClientRepository clientRepositoryMock;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    @Transactional
    @Rollback(true)
    public void testAddComplaintToAdventure(){

        FishingAdventure adventure = new FishingAdventure(FishingAdventureConstants.DB_ID, FishingAdventureConstants.DB_NAME, DB_RATING1, DB_PRICE);
        Appointment appointment = new Appointment(AppointmentConstants.DB_ID, DB_START_DATE, DB_END_DATE, DB_EXPIRATION_DATE, DB_MAX_PERSONS, DB_PRICE, DB_RESERVED, DB_DELETED, DB_IS_ACTION, null,null,adventure);
        User user = new User(DB_PASS, DB_EMAIL, ClientConstants.DB_NAME, DB_SURNAME, DB_PHONE);
        user.setId(ClientConstants.DB_ID);
        Client client = new Client(user);

        Reservation reservation = new Reservation(client, appointment);
        reservation.setId(DB_ID);

        Reservation newReservation = new Reservation(client, appointment);
        newReservation.setId(DB_ID);


        CommentDTO commentDTO = new CommentDTO("Odlican smestaj", DB_EMAIL, DB_ID, 4, false);


        when(reservationRepositoryMock.findById(DB_ID)).thenReturn(Optional.of(reservation));
        when(clientRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(client);
        when(reservationRepositoryMock.save(reservation)).thenReturn(newReservation);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertTrue(reservationService.addComplaint(commentDTO));

        verify(clientRepositoryMock, times(1)).findByEmail(DB_EMAIL);
        verify(reservationRepositoryMock, times(1)).findById(DB_ID);
        verifyNoMoreInteractions(clientRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddComplaintWhenAlreadyExists(){

        FishingAdventure adventure = new FishingAdventure(FishingAdventureConstants.DB_ID, FishingAdventureConstants.DB_NAME, DB_RATING1, DB_PRICE);
        Appointment appointment = new Appointment(AppointmentConstants.DB_ID, DB_START_DATE, DB_END_DATE, DB_EXPIRATION_DATE, DB_MAX_PERSONS, DB_PRICE, DB_RESERVED, DB_DELETED, DB_IS_ACTION, null,null,adventure);
        User user = new User(DB_PASS, DB_EMAIL, ClientConstants.DB_NAME, DB_SURNAME, DB_PHONE);
        user.setId(ClientConstants.DB_ID);
        Client client = new Client(user);

        Reservation reservation = new Reservation(client, appointment);
        reservation.setId(DB_ID);
        reservation.setComplaintEntity(Boolean.TRUE);

        Reservation newReservation = new Reservation(client, appointment);
        newReservation.setId(DB_ID);


        CommentDTO commentDTO = new CommentDTO("Odlican smestaj", DB_EMAIL, DB_ID, 4, false);


        when(reservationRepositoryMock.findById(DB_ID)).thenReturn(Optional.of(reservation));
        when(clientRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(client);
        when(reservationRepositoryMock.save(reservation)).thenReturn(newReservation);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertFalse(reservationService.addComplaint(commentDTO));

        verify(clientRepositoryMock, times(1)).findByEmail(DB_EMAIL);
        verify(reservationRepositoryMock, times(1)).findById(DB_ID);
        verifyNoMoreInteractions(clientRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddReviewToAdventure(){

        FishingAdventure adventure = new FishingAdventure(FishingAdventureConstants.DB_ID, FishingAdventureConstants.DB_NAME, DB_RATING1, DB_PRICE);
        Appointment appointment = new Appointment(AppointmentConstants.DB_ID, DB_START_DATE, DB_END_DATE, DB_EXPIRATION_DATE, DB_MAX_PERSONS, DB_PRICE, DB_RESERVED, DB_DELETED, DB_IS_ACTION, null,null,adventure);
        User user = new User(DB_PASS, DB_EMAIL, ClientConstants.DB_NAME, DB_SURNAME, DB_PHONE);
        user.setId(ClientConstants.DB_ID);
        Client client = new Client(user);

        Reservation reservation = new Reservation(client, appointment);
        reservation.setId(DB_ID);

        Reservation newReservation = new Reservation(client, appointment);
        newReservation.setId(DB_ID);


        CommentDTO commentDTO = new CommentDTO("Odlican smestaj", DB_EMAIL, DB_ID, 4, false);
        Review review = new Review(commentDTO.getContent(), commentDTO.getRate(), client, reservation.getId(), false);

        when(reservationRepositoryMock.findById(DB_ID)).thenReturn(Optional.of(reservation));
        when(fishingAdventureRepositoryMock.findById(adventure.getId())).thenReturn(Optional.of(adventure));
        when(clientRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(client);
        when(reservationRepositoryMock.save(reservation)).thenReturn(newReservation);
        when(reservationRepositoryMock.save(reservation)).thenReturn(newReservation);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertTrue(reservationService.addReview(commentDTO));

        verify(clientRepositoryMock, times(1)).findByEmail(DB_EMAIL);
        verify(reservationRepositoryMock, times(1)).findById(DB_ID); 
        verifyNoMoreInteractions(clientRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddReviewWhenAlreadyExists(){

        FishingAdventure adventure = new FishingAdventure(FishingAdventureConstants.DB_ID, FishingAdventureConstants.DB_NAME, DB_RATING1, DB_PRICE);
        Appointment appointment = new Appointment(AppointmentConstants.DB_ID, DB_START_DATE, DB_END_DATE, DB_EXPIRATION_DATE, DB_MAX_PERSONS, DB_PRICE, DB_RESERVED, DB_DELETED, DB_IS_ACTION, null,null,adventure);
        User user = new User(DB_PASS, DB_EMAIL, ClientConstants.DB_NAME, DB_SURNAME, DB_PHONE);
        user.setId(ClientConstants.DB_ID);
        Client client = new Client(user);

        Reservation reservation = new Reservation(client, appointment);
        reservation.setId(DB_ID);
        reservation.setCommentedEntity(Boolean.TRUE);

        Reservation newReservation = new Reservation(client, appointment);
        newReservation.setId(DB_ID);


        CommentDTO commentDTO = new CommentDTO("Odlican smestaj", DB_EMAIL, DB_ID, 4, false);


        when(reservationRepositoryMock.findById(DB_ID)).thenReturn(Optional.of(reservation));
        when(fishingAdventureRepositoryMock.findById(adventure.getId())).thenReturn(Optional.of(adventure));
        when(clientRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(client);
        when(reservationRepositoryMock.save(reservation)).thenReturn(newReservation);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertFalse(reservationService.addReview(commentDTO));

        verify(clientRepositoryMock, times(1)).findByEmail(DB_EMAIL);
        verify(reservationRepositoryMock, times(1)).findById(DB_ID);
        verifyNoMoreInteractions(clientRepositoryMock);
    }

}
