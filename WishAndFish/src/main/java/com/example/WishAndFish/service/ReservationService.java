package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.ReservationDTO;
import com.example.WishAndFish.dto.SearchClientDTO;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.Reservation;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}
