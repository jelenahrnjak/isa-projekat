package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.Reservation;
import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.CreateReservationDTO;
import com.example.WishAndFish.dto.ReservationDTO;
import com.example.WishAndFish.dto.SearchClientDTO;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.plaf.ScrollPaneUI;
import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AdditionalServiceService additionalServiceService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CottageRepository cottageRepository;

    @Autowired
    BoatRepository boatRepository;

    @Autowired
    FishingAdventureRepository adventureRepository;

    @Autowired
    CottageOwnerRepository cottageOwnerRepository;

    @Autowired
    BoatOwnerRepository boatOwnerRepository;

    @Autowired
    FishingInstructorRepository instructorRepository;

    @Autowired
    AdditionalServiceRepository additionalServiceRepository;


    public List<ReservationDTO> findAll() {

        List<ReservationDTO> ret = new ArrayList<>();
        for (Reservation r : reservationRepository.findAll()) {
            ret.add(new ReservationDTO(r));
        }
        return ret;
    }

    public ReservationDTO getById(Long id) {
        Reservation r = reservationRepository.findById(id).orElse(null);
        return new ReservationDTO(r);
    }


    public List<ReservationDTO> getAllByCottage(Long id) {
        List<ReservationDTO> ret = new ArrayList<>();
        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getCottage() != null) {
                if (id.equals(r.getAppointment().getCottage().getId()) && !r.getCanceled()) {
                    if (r.getAppointment().getEndDate().isBefore(LocalDateTime.now())) {
                        r.setFinished(true);
                    }
                    ret.add(new ReservationDTO(r));
                }
            }
        }
        return ret;
    }

    public List<ReservationDTO> getAllByBoat(Long id) {
        List<ReservationDTO> ret = new ArrayList<>();
        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getBoat() != null) {
                if (id.equals(r.getAppointment().getBoat().getId()) && !r.getCanceled()) {
                    if (r.getAppointment().getEndDate().isBefore(LocalDateTime.now())) {
                        r.setFinished(true);
                    }
                    ret.add(new ReservationDTO(r));
                }
            }
        }
        return ret;
    }

    public List<ReservationDTO> search(SearchClientDTO dto) {
        List<ReservationDTO> ret = new ArrayList<>();

        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getBoat() != null) {
                if (dto.getId().equals(r.getAppointment().getBoat().getId()) && (r.getClient().getName().toLowerCase().contains(dto.getCriteria().toLowerCase()) || r.getClient().getSurname().toLowerCase().contains(dto.getCriteria().toLowerCase()) || r.getClient().getEmail().toLowerCase().contains(dto.getCriteria().toLowerCase()))) {
                    if (r.getAppointment().getEndDate().isBefore(LocalDateTime.now())) {
                        r.setFinished(true);
                        reservationRepository.save(r);

                    }
                    ret.add(new ReservationDTO(r));
                }
            }
        }
        return ret;
    }

    public List<ReservationDTO> searchCottage(SearchClientDTO dto) {
        List<ReservationDTO> ret = new ArrayList<>();

        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getCottage() != null) {
                if (dto.getId().equals(r.getAppointment().getCottage().getId()) && (r.getClient().getName().toLowerCase().contains(dto.getCriteria().toLowerCase()) || r.getClient().getSurname().toLowerCase().contains(dto.getCriteria().toLowerCase()) || r.getClient().getEmail().toLowerCase().contains(dto.getCriteria().toLowerCase()))) {
                    if (r.getAppointment().getEndDate().isBefore(LocalDateTime.now())) {
                        r.setFinished(true);
                        reservationRepository.save(r);
                    }
                    ret.add(new ReservationDTO(r));
                }
            }
        }
        return ret;
    }

    public List<ClientDTO> getAllAvailableClientsBoat(Long id) {
        List<ClientDTO> ret = new ArrayList<ClientDTO>();

        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getBoat() != null) {
                if (id.equals(r.getAppointment().getBoat().getId()) && r.getAppointment().getStartDate().isBefore(LocalDateTime.now()) && r.getAppointment().getEndDate().isAfter(LocalDateTime.now())) {
                    ret.add(new ClientDTO(r.getClient()));
                }
            }
        }

        return ret;
    }


//    public Map<String, Integer> getNumberofReservationMonthlyCottage(Long id){
//        Map<String,Integer> map=new HashMap<>();
//        for(Reservation r: reservationRepository.findAll()){
//            if(r.getAppointment().getCottage() != null){
//                if(!map.containsKey(r.getAppointment().getStartDate().getMonth().toString())){
//                    Integer n = countReservationPerMonthCottage(r.getAppointment().getStartDate().getMonth().toString(), id);
//                    map.put(r.getAppointment().getStartDate().getMonth().toString(), n);
//                }
//            }
//        }
//
//        return map;
//    }

    public Map<String, Integer> getNumberofReservationMonthlyCottage(MonthReportDTO dto) {
        Map<String, Integer> map = new HashMap<>();
        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getCottage() != null) {
                if (!map.containsKey(r.getAppointment().getStartDate().getMonth().toString())) {
                    Integer n = countReservationPerMonthCottage(r.getAppointment().getStartDate().getMonth().toString(), Integer.parseInt(dto.getYear()), dto.getId());
                    map.put(r.getAppointment().getStartDate().getMonth().toString(), n);
                }
            }
        }

        return map;
    }

    public Map<String, Integer> getNumberofReservationMonthlyBoat(MonthReportDTO dto) {
        Map<String, Integer> map = new HashMap<>();
        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getBoat() != null) {
                if (!map.containsKey(r.getAppointment().getStartDate().getMonth().toString())) {
                    Integer n = countReservationPerMonthBoat(r.getAppointment().getStartDate().getMonth().toString(), Integer.parseInt(dto.getYear()), dto.getId());
                    map.put(r.getAppointment().getStartDate().getMonth().toString(), n);
                }
            }
        }

        return map;
    }

    public Map<Integer, Integer> getNumberofReservationYearlyCottage(Long id) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getCottage() != null) {
                if (!map.containsKey(r.getAppointment().getStartDate().getYear())) {
                    Integer n = countReservationPerYearCottage(r.getAppointment().getStartDate().getYear(), id);
                    map.put(r.getAppointment().getStartDate().getYear(), n);
                }
            }
        }

        return map;
    }


    public Map<Integer, Integer> getNumberofReservationYearlyBoat(Long id) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getBoat() != null) {
                if (!map.containsKey(r.getAppointment().getStartDate().getYear())) {
                    Integer n = countReservationPerYearBoat(r.getAppointment().getStartDate().getYear(), id);
                    map.put(r.getAppointment().getStartDate().getYear(), n);
                }
            }
        }

        return map;
    }

    public Integer getNumberofReservationWeeklyCottage(WeekReportDTO dto) {
        Integer n = 0;
        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getCottage() != null) {
                if (r.getAppointment().getStartDate().isAfter(findDate(dto.getStartDate())) && r.getAppointment().getStartDate().isBefore(findDate(dto.getEndDate()))) {
                    n++;
                }
            }
        }

        return n;
    }


    public Map<String, Integer> getNumberofReservationSpecificWeekCottage(WeekReportDTO dto) {
        Map<String, Integer> ret = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while (start.isBefore(end) || start.isEqual(end)) {
            for (Reservation r : reservationRepository.findAll()) {
                if (r.getAppointment().getCottage() != null) {
                    //if (!ret.containsKey(start.toString().substring(0,10))) { //ne udje za sledeci start ovde jer postoji vec u mapi sa 0
                    Integer n = countReservationPerSelectedWeekCottage(start, dto.getId());
                    ret.put(start.toString().substring(0, 10), n);
                    //}
                }
            }
            start = start.plusDays(1);
        }
        System.out.println("IZASLO");
        return ret;
    }


    public Map<String, Integer> getNumberofReservationSpecificWeekBoat(WeekReportDTO dto) {
        Map<String, Integer> ret = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while (start.isBefore(end) || start.isEqual(end)) {
            for (Reservation r : reservationRepository.findAll()) {
                if (r.getAppointment().getBoat() != null) {
                    //if (!ret.containsKey(start.toString().substring(0,10))) { //ne udje za sledeci start ovde jer postoji vec u mapi sa 0
                    Integer n = countReservationPerSelectedWeekBoat(start, dto.getId());
                    ret.put(start.toString().substring(0, 10), n);
                    //}
                }
            }
            start = start.plusDays(1);
        }
        System.out.println("IZASLO");
        return ret;
    }


    public Map<String, Double> getIncomeInSpecificPeriod(WeekReportDTO dto) {
        Map<String, Double> ret = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while (start.isBefore(end) || start.isEqual(end)) {
            for (Reservation r : reservationRepository.findAll()) {
                if (r.getAppointment().getCottage() != null) {
                    Double n = countIncome(start, dto.getId());
                    ret.put(start.toString().substring(0, 10), n);
                }
            }
            start = start.plusDays(1);
        }
        return ret;
    }

    public Map<String, Double> getIncomeInSpecificPeriodBoat(WeekReportDTO dto) {
        Map<String, Double> ret = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while (start.isBefore(end) || start.isEqual(end)) {
            for (Reservation r : reservationRepository.findAll()) {
                if (r.getAppointment().getBoat() != null) {
                    Double n = countIncomeBoat(start, dto.getId());
                    ret.put(start.toString().substring(0, 10), n);
                }
            }
            start = start.plusDays(1);
        }
        return ret;
    }

    private Double countIncome(LocalDateTime date, Long id) {
        Double income = 0.0;

        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getCottage() != null) {
                if (r.getAppointment().getStartDate().isEqual(date) && id.equals(r.getAppointment().getCottage().getId())) {
                    income += r.getTotalPrice();
                }
            }
        }

        return income;
    }

    private Double countIncomeBoat(LocalDateTime date, Long id) {
        Double income = 0.0;

        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getBoat() != null) {
                if (r.getAppointment().getStartDate().isEqual(date) && id.equals(r.getAppointment().getBoat().getId())) {
                    income += r.getTotalPrice();
                }
            }
        }

        return income;
    }

    private Integer countReservationPerSelectedWeekCottage(LocalDateTime date, Long id) {
        Integer n = 0;
        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getCottage() != null) {
                if (r.getAppointment().getStartDate().isEqual(date) && id.equals(r.getAppointment().getCottage().getId())) {
                    n++;
                }
            }
        }
        return n;
    }

    private Integer countReservationPerSelectedWeekBoat(LocalDateTime date, Long id) {
        Integer n = 0;
        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getBoat() != null) {
                if (r.getAppointment().getStartDate().isEqual(date) && id.equals(r.getAppointment().getBoat().getId())) {
                    n++;
                }
            }
        }
        return n;
    }


    private LocalDateTime findDate(String start) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(start, formatter);
    }


    private Integer countReservationPerMonthCottage(String month, Integer year, Long id) {
        Integer n = 0;

        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getCottage() != null) {
                System.out.println("mjesec: " + r.getAppointment().getStartDate().getMonth());
                if (id.equals(r.getAppointment().getCottage().getId()) && r.getAppointment().getStartDate().getMonth().toString().equals(month) && r.getAppointment().getStartDate().getYear() == year) {
                    n++;
                }
            }
        }
        return n;
    }

    private Integer countReservationPerMonthBoat(String month, Integer year, Long id) {
        Integer n = 0;

        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getBoat() != null) {
                System.out.println("mjesec: " + r.getAppointment().getStartDate().getMonth());
                if (id.equals(r.getAppointment().getBoat().getId()) && r.getAppointment().getStartDate().getMonth().toString().equals(month) && r.getAppointment().getStartDate().getYear() == year) {
                    n++;
                }
            }
        }
        return n;
    }

    private Integer countReservationPerYearCottage(Integer year, Long id) {
        Integer n = 0;

        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getCottage() != null) {
                if (id.equals(r.getAppointment().getCottage().getId()) && r.getAppointment().getStartDate().getYear() == year) {
                    n++;
                }
            }
        }

        return n;
    }

    private Integer countReservationPerYearBoat(Integer year, Long id) {
        Integer n = 0;

        for (Reservation r : reservationRepository.findAll()) {
            if (r.getAppointment().getBoat() != null) {
                if (id.equals(r.getAppointment().getBoat().getId()) && r.getAppointment().getStartDate().getYear() == year) {
                    n++;
                }
            }
        }

        return n;
    }

    @Transactional
    public boolean createReservation(CreateReservationDTO dto) throws MessagingException, PessimisticLockingFailureException {

        Client client = clientRepository.findByEmail(dto.getUser());

        if (client == null || client.getPenalties() >= 3 || client.isDeleted()) {
            return false;
        }

        Appointment appointment = this.appointmentService.createReservation(dto);

        if (appointment == null) {
            return false;
        }

        return addReservationToClient(client, appointment);
    }

    @Transactional
    public boolean addReservationToClient(Client client, Appointment appointment) throws MessagingException {

        try {
            Reservation reservation = new Reservation(client, appointment);
            Reservation ret = reservationRepository.save(reservation);

            if (ret == null) {
                return false;
            }

            emailService.sendEmailForNewReservation(client.getEmail(), ret);


        } catch (PessimisticLockingFailureException ex) {
            return false;
        }


        return true;
    }

    public List<BookingHistoryDTO> getBookingHistory(String email) {

        Client client = clientRepository.findByEmail(email);

        if (client == null || client.isDeleted()) {
            return null;
        }

        return getHistoryForClient(client.getId());
    }

    private List<BookingHistoryDTO> getHistoryForClient(Long client) {

        List<BookingHistoryDTO> ret = new ArrayList<>();

        for (Reservation r : sortedHistory()) {
            if (r.getClient() != null && r.getClient().getId() == client && (r.getAppointment().getEndDate()).isBefore(LocalDateTime.now())) {
                ret.add(new BookingHistoryDTO(r, additionalServiceService.getAllByAppointment(r.getAppointment().getId())));
            }
        }

        return ret;
    }

    public List<BookingHistoryDTO> getUpcomingReservations(String email) {

        Client client = clientRepository.findByEmail(email);

        if (client == null || client.isDeleted()) {
            return null;
        }

        return getUpcomingReservationsForClient(client.getId());
    }

    private List<BookingHistoryDTO> getUpcomingReservationsForClient(Long client) {

        List<BookingHistoryDTO> ret = new ArrayList<>();

        for (Reservation r : sortedReservations()) {
            if (r.getClient() != null && r.getClient().getId() == client && (r.getAppointment().getEndDate()).isAfter(LocalDateTime.now())) {
                ret.add(new BookingHistoryDTO(r, additionalServiceService.getAllByAppointment(r.getAppointment().getId())));
            }
        }

        return ret;
    }

    public boolean addReview(CommentDTO dto) {

        Client client = clientRepository.findByEmail(dto.getClient());
        Reservation reservation = reservationRepository.findById(dto.getReservationID()).orElseGet(null);

        if (client == null || reservation == null) {
            return false;
        }

        if ((reservation.getCommentedEntity() && !dto.getIsOwner()) || (reservation.getCommentedOwner() && dto.getIsOwner())) {
            return false;
        }

        if (dto.getIsOwner()) {

            reservation.setCommentedOwner(Boolean.TRUE);
        } else {

            reservation.setCommentedEntity(Boolean.TRUE);
        }

        Reservation newReservation = this.reservationRepository.save(reservation);

        Review newReview = new Review(dto.getContent(), dto.getRate(), client, newReservation.getId(), dto.getIsOwner());

        this.reviewRepository.save(newReview);

        return changeRating(newReservation, newReview);

    }

    private boolean changeRating(Reservation r, Review review) {

        Appointment a = r.getAppointment();

        if (a.getCottage() != null) {

            return changeCottageRating(a.getCottage(), review);

        } else if (a.getBoat() != null) {

            return changeBoatRating(a.getBoat(), review);

        } else if (a.getFishingAdventure() != null) {

            return changeAdventureRating(a.getFishingAdventure(), review);

        }

        return false;

    }

    private boolean changeCottageRating(Cottage cottage, Review review) {

        if (review.isForOwner()) {

            CottageOwner edited = cottageOwnerRepository.findById(cottage.getCottageOwner().getId()).orElseGet(null);
            if (edited == null) {
                return false;
            }

            edited.setRating(newRating(edited.getRating(), review.getRating(), edited.getNumberOfRatings()));
            edited.setNumberOfRatings(edited.getNumberOfRatings() + 1);
            cottageOwnerRepository.save(edited);


        } else {
            Cottage edited = cottageRepository.findById(cottage.getId()).orElseGet(null);
            if (edited == null) {
                return false;
            }

            edited.setRating(newRating(edited.getRating(), review.getRating(), edited.getNumberOfRatings()));
            edited.setNumberOfRatings(edited.getNumberOfRatings() + 1);
            cottageRepository.save(edited);
        }
        return true;
    }

    private boolean changeBoatRating(Boat boat, Review review) {

        if (review.isForOwner()) {

            BoatOwner edited = boatOwnerRepository.findById(boat.getBoatOwner().getId()).orElseGet(null);
            if (edited == null) {
                return false;
            }

            edited.setRating(newRating(edited.getRating(), review.getRating(), edited.getNumberOfRatings()));
            edited.setNumberOfRatings(edited.getNumberOfRatings() + 1);
            boatOwnerRepository.save(edited);

        } else {
            Boat edited = boatRepository.findById(boat.getId()).orElseGet(null);
            if (edited == null) {
                return false;
            }

            edited.setRating(newRating(edited.getRating(), review.getRating(), edited.getNumberOfRatings()));
            edited.setNumberOfRatings(edited.getNumberOfRatings() + 1);
            boatRepository.save(edited);
        }
        return true;

    }

    private boolean changeAdventureRating(FishingAdventure adventure, Review review) {

        if (review.isForOwner()) {

            FishingInstructor edited = instructorRepository.findById(adventure.getFishingInstructor().getId()).orElseGet(null);
            if (edited == null) {
                return false;
            }

            edited.setRating(newRating(edited.getRating(), review.getRating(), edited.getNumberOfRatings()));
            edited.setNumberOfRatings(edited.getNumberOfRatings() + 1);
            instructorRepository.save(edited);

        } else {
            FishingAdventure edited = adventureRepository.findById(adventure.getId()).orElseGet(null);
            if (edited == null) {
                return false;
            }

            edited.setRating(newRating(edited.getRating(), review.getRating(), edited.getNumberOfRatings()));
            edited.setNumberOfRatings(edited.getNumberOfRatings() + 1);
            adventureRepository.save(edited);
        }

        return true;
    }


    public Double newRating(Double oldRate, Integer newRate, Integer num) {

        Double ret = ((oldRate * num) + newRate) / (num + 1);

        BigDecimal bd = new BigDecimal(ret).setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }


    public List<Reservation> sortedReservations() {

        List<Reservation> ret = new ArrayList<>();
        List<Reservation> allReservations = reservationRepository.findAll();

        if (allReservations.size() < 2) {
            return allReservations;
        }

        ret.add(allReservations.get(0));

        for (int i = 1; i < allReservations.size(); i++) {
            if (ret.get(i - 1).getAppointment().getStartDate().isBefore(allReservations.get(i).getAppointment().getStartDate())) {
                ret.add(allReservations.get(i));
            } else {
                Reservation pom = ret.get(i - 1);
                ret.set(i - 1, allReservations.get(i));
                ret.add(pom);
            }
        }

        return ret;
    }

    public List<Reservation> sortedHistory() {

        List<Reservation> ret = new ArrayList<>();
        List<Reservation> allReservations = reservationRepository.findAll();

        if (allReservations.size() < 2) {
            return allReservations;
        }

        ret.add(allReservations.get(0));

        for (int i = 1; i < allReservations.size(); i++) {
            if (allReservations.get(i).getAppointment().getStartDate().isBefore(ret.get(i - 1).getAppointment().getStartDate())) {
                ret.add(allReservations.get(i));
            } else {
                Reservation pom = ret.get(i - 1);
                ret.set(i - 1, allReservations.get(i));
                ret.add(pom);
            }
        }

        return ret;
    }

    public boolean addComplaint(CommentDTO dto) {

        Client client = clientRepository.findByEmail(dto.getClient());
        Reservation reservation = reservationRepository.findById(dto.getReservationID()).orElseGet(null);

        if (client == null || reservation == null) {
            return false;
        }

        if ((reservation.getComplaintEntity() && !dto.getIsOwner()) || (reservation.getComplaintOwner() && dto.getIsOwner())) {
            return false;
        }

        if (dto.getIsOwner()) {

            reservation.setComplaintOwner(Boolean.TRUE);
        } else {

            reservation.setComplaintEntity(Boolean.TRUE);
        }

        Reservation newReservation = this.reservationRepository.save(reservation);

        Complaint newComplaint = new Complaint(dto.getContent(), client, newReservation.getId(), dto.getIsOwner());

        this.complaintRepository.save(newComplaint);

        return true;
    }

    @Transactional
    public boolean bookAction(ActionReservationDTO dto) throws MessagingException {

        Client client = clientRepository.findByEmail(dto.getClient());
        if (client == null || client.getPenalties() >= 3 || client.isDeleted()) {
            return false;
        }

        try {
            Appointment appointment = appointmentRepository.findOneById(dto.getAction());


            if (appointment == null || !appointment.getIsAction() || appointment.isDeleted() || appointment.getReserved()) {
                return false;
            }

            appointment.setReserved(Boolean.TRUE);
            appointmentRepository.save(appointment);

            return addReservationToClient(client, appointment);

        } catch (PessimisticLockingFailureException ex) {

            throw new PessimisticLockingFailureException("Action already booked!");

        }

    }

    public boolean cancelReservation(ActionReservationDTO dto) {


        Reservation reservation = reservationRepository.findById(dto.getAction()).orElseGet(null);

        if (reservation == null || reservation.getCanceled()) {
            return false;
        }

        if (reservation.getAppointment().getStartDate().isBefore(LocalDateTime.now())) {// || !reservation.getAppointment().getReserved()){
            return false;
        }

        if (reservation.getAppointment().getIsAction()) {
            return cancelAction(reservation);
        }

        return cancelNormalReservation(reservation);

    }

    public boolean cancelNormalReservation(Reservation reservation) {

        Appointment appointment = appointmentRepository.findById(reservation.getAppointment().getId()).orElseGet(null);

        if (appointment == null) {
            return false;
        }

        Long id = reservation.getId();

        appointmentService.cancelNormalReservation(appointment);

        Reservation editedReservation = reservationRepository.findById(id).orElseGet(null);
        if (editedReservation == null) {
            return false;
        }

        editedReservation.setCanceled(true);
        reservationRepository.save(editedReservation);

        return true;


    }

    public boolean cancelAction(Reservation reservation) {

        Appointment appointment = appointmentRepository.findById(reservation.getAppointment().getId()).orElseGet(null);

        if (appointment == null) {
            return false;
        }

        reservation.setCanceled(true);
        reservationRepository.save(reservation);

        Appointment newAppointment = new Appointment(appointment);
        newAppointment.setReserved(false);
        appointmentRepository.save(newAppointment);

        for (AdditionalService service : additionalServiceRepository.findAll()) {

            if (service.getAppointments() == null) {
                continue;
            }
            addAdditionalService(newAppointment, service, appointment.getId());

        }

        return true;
    }

    public void addAdditionalService(Appointment newAppointment, AdditionalService service, Long oldAppointment) {

        for (Appointment a : service.getAppointments()) {
            if (a.getId() == oldAppointment) {
                service.getAppointments().add(newAppointment);
                additionalServiceRepository.save(service);
                return;
            }
        }
    }
}
