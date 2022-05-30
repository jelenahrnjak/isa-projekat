package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.CommentDTO;
import com.example.WishAndFish.model.Client;
import com.example.WishAndFish.model.Comment;
import com.example.WishAndFish.model.Reservation;
import com.example.WishAndFish.repository.ClientRepository;
import com.example.WishAndFish.repository.CommentRepository;
import com.example.WishAndFish.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public Comment addCommentToClient(CommentDTO comment){
        Comment c = new Comment();
        Client client = clientRepository.findByEmail(comment.getClient());

        c.setContent(comment.getContent());
        c.setCame(comment.getCame());
        c.setBadComment(comment.getBad());

        if(!comment.getCame()){
            c.setApproved(true);
            c.setAddPenalty(true);
            client.setPenalties(client.getPenalties() + 1);
            clientRepository.save(client);
        }

        //admin treba da odobri
        if(!comment.getBad()){
            c.setApproved(true);
            c.setAddPenalty(false);
        }
        else{
            c.setApproved(false);
            c.setAddPenalty(true);
        }

        c.setClient(client);
        commentRepository.save(c);

        Reservation r = this.reservationRepository.findById(comment.getReservationID()).orElse(null);
        if(r != null){
            r.setCommented(true);
            reservationRepository.save(r);
        }

        return c;
    }
}
