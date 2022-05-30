package com.example.WishAndFish.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "reservation_id", nullable = false)
    private Long reservationId;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "isForOwner", nullable = false)
    private boolean isForOwner;

    @Column(name = "isReviewed", nullable = false)
    private boolean isReviewed;

    @Column(name = "isApproved", nullable = false)
    private boolean isApproved;

    public Review(String content, Integer rating, Client client, Long reservation, boolean isForOwner){
        this.content = content;
        this.rating = rating;
        this.client = client;
        this.reservationId = reservation;
        this.date = LocalDateTime.now();
        this.isForOwner = isForOwner;
        this.isReviewed = true;
        this.isApproved = true;
    }
}
