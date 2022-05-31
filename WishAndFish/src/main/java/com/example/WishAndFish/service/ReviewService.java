package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.ReviewDTO;
import com.example.WishAndFish.model.Reservation;
import com.example.WishAndFish.model.Review;
import com.example.WishAndFish.repository.ReservationRepository;
import com.example.WishAndFish.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public List<ReviewDTO> getAllCommentsCottage(Long id){
        List<ReviewDTO> ret = new ArrayList<>();

        for(Review r: reviewRepository.findAll()){
            if(r.isApproved() && !r.isForOwner()){
                Reservation reservation = reservationRepository.findById(r.getReservationId()).orElseGet(null);
                if(reservation.getAppointment().getCottage() != null){
                    if(id.equals(reservation.getAppointment().getCottage().getId())){
                        ret.add(new ReviewDTO(r));
                    }
                }
            }

        }

        return ret;
    }
}
