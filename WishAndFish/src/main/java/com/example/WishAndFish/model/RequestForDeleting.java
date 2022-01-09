package com.example.WishAndFish.model;

import javax.persistence.*;

@Entity
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

    public RequestForDeleting() {
    }

    public RequestForDeleting(String reason, boolean approved, User user) {
        this.reason = reason;
        this.approved = approved;
        this.user = user;
        this.processed = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}

