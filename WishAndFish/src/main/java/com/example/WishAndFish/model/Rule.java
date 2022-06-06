package com.example.WishAndFish.model;

import com.example.WishAndFish.dto.RuleDTO;
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
@Table(name = "rules")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content", unique = false, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cottage_id")
    @JsonBackReference
    private Cottage cottage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "boat_id")
    @JsonBackReference
    private Boat boat;

    @Column(name = "deleted", unique = false, nullable = false)
    private Boolean deleted;

    public Rule(RuleDTO ruleDTO){
        this.content = ruleDTO.getContent();
    }



    public Rule(String content, Cottage cottage, Boat boat) {
        this.content = content;
        this.cottage = cottage;
        this.boat = boat;
    }

    public Rule(Rule rule) {
        this.content = rule.getContent();
        this.cottage = rule.getCottage();
        this.boat = rule.getBoat();
        this.deleted = rule.getDeleted();
    }
}
