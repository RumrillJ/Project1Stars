package com.revature.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Project Lombok
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "stars")
@Component
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int starId;

    @Column(nullable = false, unique = true)
    private String starName;

    @Column(nullable = false)
    private String starColor;


    @OneToMany(mappedBy = "star", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Planet> planets;


}
