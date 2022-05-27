package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.Reservation;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.plaf.ScrollPaneUI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CottageRepository cottageRepository;

    public List<ReservationDTO> findAll() {

        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation r : reservationRepository.findAll()){
                ret.add(new ReservationDTO(r));
        }
        return ret;
    }

    public ReservationDTO getById(Long id){
        Reservation r = reservationRepository.findById(id).orElse(null);
        return new ReservationDTO(r);
    }


    public List<ReservationDTO> getAllByCottage(Long id){
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null){
                if(id.equals(r.getAppointment().getCottage().getId())){
                    if(r.getAppointment().getEndDate().isBefore(LocalDateTime.now())){
                        r.setFinished(true);
                    }
                    ret.add(new ReservationDTO(r));
                }
            }
        }
       return ret;
    }

    public List<ReservationDTO> getAllByBoat(Long id){
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null){
                if(id.equals(r.getAppointment().getBoat().getId())){
                    if(r.getAppointment().getEndDate().isBefore(LocalDateTime.now())){
                        r.setFinished(true);
                    }
                    ret.add(new ReservationDTO(r));
                }
            }
        }
        return ret;
    }

    public List<ReservationDTO> search(SearchClientDTO dto){
        List<ReservationDTO> ret = new ArrayList<>();

        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null){
                if(dto.getId().equals(r.getAppointment().getBoat().getId()) && (r.getClient().getName().toLowerCase().contains(dto.getCriteria().toLowerCase()) || r.getClient().getSurname().toLowerCase().contains(dto.getCriteria().toLowerCase())  || r.getClient().getEmail().toLowerCase().contains(dto.getCriteria().toLowerCase())) ){
                    ret.add(new ReservationDTO(r));
                }
            }
        }
        return ret;
    }

    public List<ReservationDTO> searchCottage(SearchClientDTO dto){
        List<ReservationDTO> ret = new ArrayList<>();

        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null){
                if(dto.getId().equals(r.getAppointment().getCottage().getId()) && (r.getClient().getName().toLowerCase().contains(dto.getCriteria().toLowerCase()) || r.getClient().getSurname().toLowerCase().contains(dto.getCriteria().toLowerCase())  || r.getClient().getEmail().toLowerCase().contains(dto.getCriteria().toLowerCase())) ){
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
                    if (!ret.containsKey(start.toString().substring(0,10))) {
                        Integer n = countReservationPerSelectedWeekCottage(start, dto.getId());
                        ret.put(start.toString().substring(0,10), n);
                    }
                }
            }
            start = start.plusDays(1);

        }
        System.out.println("IZASLO");
        return  ret;
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
}
