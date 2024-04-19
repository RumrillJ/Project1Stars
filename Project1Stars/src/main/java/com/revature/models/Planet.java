package com.revature.models;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "starId")
    private Star star; //many planets to one star

}
