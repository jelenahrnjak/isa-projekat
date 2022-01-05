package com.example.WishAndFish.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_date", unique = false, nullable = false)
    private Date startDate;

    @Column(name = "end_date", unique = false, nullable = false)
    private Date endDate;

    @Column(name = "max_persons", unique = false, nullable = false)
    private Integer maxPersons;

    @Column(name = "price", unique = false, nullable = false)
    private Double price;

    @Column(name = "duration", unique = false, nullable = false)
    private Duration duration;

    @Column(name = "reserved", unique = false, nullable = false)
    private Boolean reserved;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cottage_id", nullable = false)
    private Cottage cottage;

    @ManyToMany(mappedBy = "appointments")
    private Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();

    public Appointment() {
    }

    public Appointment(long id, Date startDate, Date endDate, Integer maxPersons, Double price, Duration duration, Boolean reserved) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxPersons = maxPersons;
        this.price = price;
        this.duration = duration;
        this.reserved = reserved;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
