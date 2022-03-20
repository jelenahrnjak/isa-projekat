package com.example.WishAndFish.model;

import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "additional_services")
public class AdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "price", unique = false, nullable = false)
    private String price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "appointment_additional_services",
            joinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"))
    private Set<Appointment> appointments = new HashSet<Appointment>();

}
