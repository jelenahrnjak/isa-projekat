package com.example.WishAndFish.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "password", unique = false, nullable = false)
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "name", unique = false, nullable = false)
    private String name;
    @Column(name = "surname", unique = false, nullable = false)
    private String surname; 
    @OneToOne(targetEntity = Address.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "phoneNumber", unique = false, nullable = false)
    private String phoneNumber;
    @Column(name = "deleted", unique = false, nullable = false)
    private boolean deleted;
    @Column(name = "enabled", unique = false, nullable = false)
    private boolean enabled;
    @Column(name = "points", unique = false, nullable = false)
    private double points;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "loyalty_category_id", nullable = false)
    private LoyaltyCategory loyaltyCategory;
    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    @Column(name = "reason_for_registration")
    private String reasonForRegistration;
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
    }

    public User(User u){
        this.password = u.getPassword();
        this.email = u.getEmail();
        this.name = u.getName();
        this.surname = u.getSurname();
        this.phoneNumber = u.getPhoneNumber();
        this.deleted = u.isDeleted();
        this.enabled = u.isEnabled();
        this.points = u.getPoints();
        this.loyaltyCategory = u.getLoyaltyCategory();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
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

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getPoints() {return points; }

    public void setPoints(double points) {
        this.points = points;
//        if(points > 500) {
//            this.loyaltyCategory = LoyaltyCategories.SILVER;
//            this.discount = 5;
//        }
//        if(points > 1500) {
//            this.discount = 15;
//            this.loyaltyCategory = LoyaltyCategories.GOLD;
//        }
    }

    public LoyaltyCategory getLoyaltyCategory() { return loyaltyCategory; }

    public void setLoyaltyCategory(LoyaltyCategory loyaltyCategory) { this.loyaltyCategory = loyaltyCategory; }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getReasonForRegistration() {
        return reasonForRegistration;
    }

    public void setReasonForRegistration(String reasonForRegistration) {
        this.reasonForRegistration = reasonForRegistration;
    }

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

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> collection = new ArrayList<Role>();
        collection.add(this.role);
        return collection;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
