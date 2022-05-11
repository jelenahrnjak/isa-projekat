package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AddCottageDTO;
import com.example.WishAndFish.dto.CommentDTO;
import com.example.WishAndFish.model.Comment;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.service.CommentService;
import com.example.WishAndFish.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/comments")
@CrossOrigin()
public class CommentController {

    @Autowired
    CommentService commentService;


    @PostMapping(value = "/addCommentToClient")
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<Comment> addCommentToClient(@RequestBody CommentDTO comment) {
        Comment c = this.commentService.addCommentToClient(comment);
        if(c == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
}
