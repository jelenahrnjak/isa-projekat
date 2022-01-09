package com.example.WishAndFish.model;

import com.example.WishAndFish.dto.RuleDTO;

import javax.persistence.*;

@Entity
@Table(name = "rules")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content", unique = false, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cottage_id")
    private Cottage cottage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "boat_id")
    private Boat boat;

    public Rule(RuleDTO ruleDTO){
        this.content = ruleDTO.getContent();
    }

    public Rule() {
    }

    public Rule(String content, Cottage cottage, Boat boat) {
        this.content = content;
        this.cottage = cottage;
        this.boat = boat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
