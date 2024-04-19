package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="planets")
@Component
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planetId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int mass; //just use KG

    //cascade type merge because if we delete the last planet in a star,
    //we do NOT want the star to be deleted as well
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "starId")
    @JsonBackReference
    private Star star; //many planets to one star


    @Transient //we cannot get starId to be returned in our responses
    //because jsonBackReference prevents it from happening
    //We can do it, if we ignore it from the database.
    public int getStarId() {
        return this.star.getStarId();
    }

}
