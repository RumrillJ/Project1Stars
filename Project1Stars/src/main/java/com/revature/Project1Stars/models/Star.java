package com.revature.Project1Stars.models;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

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

    @Column(nullable = false)
    private int planetID;

}
