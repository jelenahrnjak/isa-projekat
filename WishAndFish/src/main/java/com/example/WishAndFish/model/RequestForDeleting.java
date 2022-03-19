package com.example.WishAndFish.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "request_for_deleting")
public class RequestForDeleting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reason", unique = false, nullable = false)
    private String reason;

    @Column(name= "approved" , unique = false, nullable = false)
    private boolean approved;

    @Column(name= "processed" , unique = false, nullable = false)
    private boolean processed;

    @OneToOne(targetEntity = User.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;


    public RequestForDeleting(String reason, boolean approved, User user) {
        this.reason = reason;
        this.approved = approved;
        this.user = user;
        this.processed = false;
    }


}

