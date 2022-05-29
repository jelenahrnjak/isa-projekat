package com.example.WishAndFish.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

    @Column(name = "bad")
    private Boolean badComment;

    @Column(name = "came")
    private Boolean came;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "addPenalty")
    private Boolean addPenalty;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    private Client client;
}
