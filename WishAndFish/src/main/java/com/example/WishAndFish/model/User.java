package com.example.WishAndFish.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    public enum LoyaltyCategories {
        REGULAR,
        SILVER,
        GOLD;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "password", unique = false, nullable = false)
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "name", unique = false, nullable = false)
    private String name;
    @Column(name = "surname", unique = false, nullable = false)
    private String surname;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "phoneNumber", unique = true, nullable = false)
    private String phoneNumber;
    @Column(name = "deleted", unique = false, nullable = false)
    private boolean deleted;
    @Column(name = "enabled", unique = false, nullable = false)
    private boolean enabled;
    @Column(name = "points", unique = false, nullable = false)
    private double points;
    @Column(name = "loyaltyCategory", unique = false, nullable = false)
    private LoyaltyCategories loyaltyCategory;

    public User() {
        super();
    }

    public User(String password, String email, String name, String surname, String phoneNumber) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.deleted = false;
        this.enabled = false;
        this.points = 0.0;
        this.loyaltyCategory = LoyaltyCategories.REGULAR;
    }

    public User(User u){
        this.password = u.password;
        this.email = u.email;
        this.name = u.name;
        this.surname = u.surname;
        this.phoneNumber = u.phoneNumber;
        this.deleted = false;
        this.enabled = false;
        this.points = 0.0;
        this.loyaltyCategory = LoyaltyCategories.REGULAR;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getPoints() {return points; }

    public void setPoints(double points) { this.points = points; }

    public LoyaltyCategories getLoyaltyCategory() { return loyaltyCategory; }

    public void setLoyaltyCategory(LoyaltyCategories loyaltyCategory) { this.loyaltyCategory = loyaltyCategory; }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
