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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.plaf.ScrollPaneUI;
import javax.mail.MessagingException;
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
                if (id.equals(r.getAppointment().getCottage().getId())) {
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
                if (id.equals(r.getAppointment().getBoat().getId())) {
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

    public List<ClientDTO> getAllAvailableClientsBoat(Long id){
        List<ClientDTO> ret = new ArrayList<ClientDTO>();

        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null){
                if(id.equals(r.getAppointment().getBoat().getId()) && r.getAppointment().getStartDate().isBefore(LocalDateTime.now()) && r.getAppointment().getEndDate().isAfter(LocalDateTime.now())){
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

    public Map<String, Integer> getNumberofReservationMonthlyCottage(MonthReportDTO dto){
        Map<String,Integer> map=new HashMap<>();
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null){
                if(!map.containsKey(r.getAppointment().getStartDate().getMonth().toString())){
                    Integer n = countReservationPerMonthCottage(r.getAppointment().getStartDate().getMonth().toString(), Integer.parseInt(dto.getYear()), dto.getId());
                    map.put(r.getAppointment().getStartDate().getMonth().toString(), n);
                }
            }
        }

        return map;
    }

    public Map<String, Integer> getNumberofReservationMonthlyBoat(MonthReportDTO dto){
        Map<String,Integer> map=new HashMap<>();
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null){
                if(!map.containsKey(r.getAppointment().getStartDate().getMonth().toString())){
                    Integer n = countReservationPerMonthBoat(r.getAppointment().getStartDate().getMonth().toString(), Integer.parseInt(dto.getYear()), dto.getId());
                    map.put(r.getAppointment().getStartDate().getMonth().toString(), n);
                }
            }
        }

        return map;
    }

    public Map<Integer,Integer> getNumberofReservationYearlyCottage(Long id){
        Map<Integer,Integer> map=new HashMap<>();
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null) {
                if (!map.containsKey(r.getAppointment().getStartDate().getYear())) {
                    Integer n = countReservationPerYearCottage(r.getAppointment().getStartDate().getYear(), id);
                    map.put(r.getAppointment().getStartDate().getYear(), n);
                }
            }
        }

        return map;
    }


    public Map<Integer,Integer> getNumberofReservationYearlyBoat(Long id){
        Map<Integer,Integer> map=new HashMap<>();
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null) {
                if (!map.containsKey(r.getAppointment().getStartDate().getYear())) {
                    Integer n = countReservationPerYearBoat(r.getAppointment().getStartDate().getYear(), id);
                    map.put(r.getAppointment().getStartDate().getYear(), n);
                }
            }
        }

        return map;
    }

    public Integer getNumberofReservationWeeklyCottage(WeekReportDTO dto){
        Integer n = 0;
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null){
                if(r.getAppointment().getStartDate().isAfter(findDate(dto.getStartDate())) && r.getAppointment().getStartDate().isBefore(findDate(dto.getEndDate()))){
                    n++;
                }
            }
        }

        return n;
    }


    public Map<String, Integer>getNumberofReservationSpecificWeekCottage(WeekReportDTO dto){
        Map<String, Integer> ret = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while(start.isBefore(end) || start.isEqual(end)) {
            for (Reservation r : reservationRepository.findAll()) {
                if (r.getAppointment().getCottage() != null) {
                    //if (!ret.containsKey(start.toString().substring(0,10))) { //ne udje za sledeci start ovde jer postoji vec u mapi sa 0
                        Integer n = countReservationPerSelectedWeekCottage(start, dto.getId());
                        ret.put(start.toString().substring(0,10), n);
                    //}
                }
            }
            start = start.plusDays(1);
        }
        System.out.println("IZASLO");
        return  ret;
    }


    public Map<String, Integer>getNumberofReservationSpecificWeekBoat(WeekReportDTO dto){
        Map<String, Integer> ret = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while(start.isBefore(end) || start.isEqual(end)) {
            for (Reservation r : reservationRepository.findAll()) {
                if (r.getAppointment().getBoat() != null) {
                    //if (!ret.containsKey(start.toString().substring(0,10))) { //ne udje za sledeci start ovde jer postoji vec u mapi sa 0
                    Integer n = countReservationPerSelectedWeekBoat(start, dto.getId());
                    ret.put(start.toString().substring(0,10), n);
                    //}
                }
            }
            start = start.plusDays(1);
        }
        System.out.println("IZASLO");
        return  ret;
    }


    public Map<String, Double>getIncomeInSpecificPeriod(WeekReportDTO dto){
        Map<String, Double> ret = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while(start.isBefore(end) || start.isEqual(end)) {
            for (Reservation r : reservationRepository.findAll()) {
                if (r.getAppointment().getCottage() != null) {
                    Double n = countIncome(start, dto.getId());
                    ret.put(start.toString().substring(0,10), n);
                }
            }
            start = start.plusDays(1);
        }
        return  ret;
    }

    public Map<String, Double>getIncomeInSpecificPeriodBoat(WeekReportDTO dto){
        Map<String, Double> ret = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while(start.isBefore(end) || start.isEqual(end)) {
            for (Reservation r : reservationRepository.findAll()) {
                if (r.getAppointment().getBoat() != null) {
                    Double n = countIncomeBoat(start, dto.getId());
                    ret.put(start.toString().substring(0,10), n);
                }
            }
            start = start.plusDays(1);
        }
        return  ret;
    }

    private Double countIncome(LocalDateTime date, Long id){
        Double income = 0.0;

        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null){
                if(r.getAppointment().getStartDate().isEqual(date) && id.equals(r.getAppointment().getCottage().getId())){
                    income += r.getTotalPrice();
                }
            }
        }

        return income;
    }

    private Double countIncomeBoat(LocalDateTime date, Long id){
        Double income = 0.0;

        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null){
                if(r.getAppointment().getStartDate().isEqual(date) && id.equals(r.getAppointment().getBoat().getId())){
                    income += r.getTotalPrice();
                }
            }
        }

        return income;
    }

    private Integer countReservationPerSelectedWeekCottage(LocalDateTime date, Long id){
        Integer n = 0;
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null){
                if(r.getAppointment().getStartDate().isEqual(date) && id.equals(r.getAppointment().getCottage().getId())){
                    n++;
                }
            }
        }
        return n;
    }

    private Integer countReservationPerSelectedWeekBoat(LocalDateTime date, Long id){
        Integer n = 0;
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null){
                if(r.getAppointment().getStartDate().isEqual(date) && id.equals(r.getAppointment().getBoat().getId())){
                    n++;
                }
            }
        }
        return n;
    }


    private LocalDateTime findDate(String start){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(start, formatter);
    }


    private Integer countReservationPerMonthCottage(String month, Integer year, Long id){
        Integer n = 0;

        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null){
                System.out.println("mjesec: " + r.getAppointment().getStartDate().getMonth());
                if(id.equals(r.getAppointment().getCottage().getId()) && r.getAppointment().getStartDate().getMonth().toString().equals(month) && r.getAppointment().getStartDate().getYear() == year){
                    n++;
                }
            }
        }
        return n;
    }

    private Integer countReservationPerMonthBoat(String month, Integer year, Long id){
        Integer n = 0;

        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null){
                System.out.println("mjesec: " + r.getAppointment().getStartDate().getMonth());
                if(id.equals(r.getAppointment().getBoat().getId()) && r.getAppointment().getStartDate().getMonth().toString().equals(month) && r.getAppointment().getStartDate().getYear() == year){
                    n++;
                }
            }
        }
        return n;
    }

    private Integer countReservationPerYearCottage(Integer year, Long id){
        Integer n = 0;

        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null){
                if(id.equals(r.getAppointment().getCottage().getId()) && r.getAppointment().getStartDate().getYear() == year){
                    n++;
                }
            }
        }

        return n;
    }

    private Integer countReservationPerYearBoat(Integer year, Long id){
        Integer n = 0;

        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null){
                if(id.equals(r.getAppointment().getBoat().getId()) && r.getAppointment().getStartDate().getYear() == year){
                    n++;
                }
            }
        }

        return n;
    }

    @Transactional
    public boolean createReservation(CreateReservationDTO dto) throws MessagingException {

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

    private boolean addReservationToClient(Client client, Appointment appointment) throws MessagingException {

        Reservation reservation = new Reservation(client, appointment);
        Reservation ret = reservationRepository.save(reservation);

        if (ret == null) {
            return false;
        }

        emailService.sendEmailForNewReservation(client.getEmail(), ret);

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
            if (r.getClient().getId() == client && !r.getCanceled() && (r.getAppointment().getEndDate().toLocalDate()).isBefore(LocalDate.now())) {
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

        for (Reservation r :  sortedReservations()) {
            System.out.println(r.getAppointment().getStartDate());
            if (r.getClient().getId() == client && !r.getCanceled() && (r.getAppointment().getEndDate().toLocalDate()).isAfter(LocalDate.now())) {
                ret.add(new BookingHistoryDTO(r, additionalServiceService.getAllByAppointment(r.getAppointment().getId())));
            }
        }

        return ret;
    }

    public boolean addReview(CommentDTO dto) {

        Client client = clientRepository.findByEmail(dto.getClient());
        Reservation reservation = reservationRepository.findById(dto.getReservationID()).orElseGet(null);

        if(client == null  || reservation == null){
            return false;
        }

        if((reservation.getCommentedEntity() && !dto.getIsOwner()) || (reservation.getCommentedOwner() && dto.getIsOwner())){
            return false;
        }

        if(dto.getIsOwner()){

            reservation.setCommentedOwner(Boolean.TRUE);
        }else{

            reservation.setCommentedEntity(Boolean.TRUE);
        }

        Reservation newReservation = this.reservationRepository.save(reservation);

        Review newReview = new Review(dto.getContent(), dto.getRate(), client, newReservation.getId(), dto.getIsOwner());

        this.reviewRepository.save(newReview);

        return true;
    }


    public List<Reservation> sortedReservations(){

        List<Reservation> ret = new ArrayList<>();
        List<Reservation> allReservations = reservationRepository.findAll();

        if(allReservations.size() < 2){
            return allReservations;
        }

        ret.add(allReservations.get(0));

        for (int i = 1; i < allReservations.size() ; i++) {
            if(ret.get(i - 1).getAppointment().getStartDate().isBefore(allReservations.get(i).getAppointment().getStartDate())){
                ret.add(allReservations.get(i));
            }else{
                Reservation pom = ret.get(i-1);
                ret.set(i-1, allReservations.get(i));
                ret.add(pom);
            }
        }

        return ret;
    }

    public List<Reservation> sortedHistory(){

        List<Reservation> ret = new ArrayList<>();
        List<Reservation> allReservations = reservationRepository.findAll();

        if(allReservations.size() < 2){
            return allReservations;
        }

        ret.add(allReservations.get(0));

        for (int i = 1; i < allReservations.size() ; i++) {
            if(allReservations.get(i).getAppointment().getStartDate().isBefore(ret.get(i-1).getAppointment().getStartDate())){
                ret.add(allReservations.get(i));
            }else{
                Reservation pom = ret.get(i-1);
                ret.set(i-1, allReservations.get(i));
                ret.add(pom);
            }
        }

        return ret;
    }

    public boolean addComplaint(CommentDTO dto) {

        Client client = clientRepository.findByEmail(dto.getClient());
        Reservation reservation = reservationRepository.findById(dto.getReservationID()).orElseGet(null);

        if(client == null || reservation == null){
            return false;
        }

        if((reservation.getComplaintEntity() && !dto.getIsOwner()) || (reservation.getComplaintOwner() && dto.getIsOwner())){
            return false;
        }

        if(dto.getIsOwner()){

            reservation.setComplaintOwner(Boolean.TRUE);
        }else{

            reservation.setComplaintEntity(Boolean.TRUE);
        }

        Reservation newReservation = this.reservationRepository.save(reservation);

        Complaint newComplaint = new Complaint(dto.getContent(), client, newReservation.getId(), dto.getIsOwner());

        this.complaintRepository.save(newComplaint);

        return true;
    }


}
