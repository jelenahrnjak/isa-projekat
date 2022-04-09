package com.example.WishAndFish.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "path", unique = false, nullable = false)
    private String path;

    @Column(name = "deleted", unique = false, nullable = false)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cottage_id", nullable = true)
    @JsonBackReference
    private Cottage cottage;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "boat_id", nullable = true)
    @JsonBackReference
    private Boat boat;

    public Image(String path, Cottage cottage){
        this.path = path;
        this.cottage = cottage;
    }
    public Image(String path){
        this.path = path;

    }

}
