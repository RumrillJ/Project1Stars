package com.revature.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name="planets")
@Component
@Getter @Setter @NoArgsConstructor
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planetId;

    private String name;
    private int mass; //just use KG

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "starId")
//    private Star owner;


    public Planet(int planetId, String name, int mass){ //,Star star) {
        this.planetId = planetId;
        this.name = name;
        this.mass = mass;
        //this.star = star;
    }
}
