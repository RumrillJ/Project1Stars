package com.revature.controllers;

import com.revature.daos.PlanetDAO;
import com.revature.models.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private PlanetDAO planetDAO;

    @Autowired
    public PlanetController(PlanetDAO planetDAO){
        this.planetDAO = planetDAO;
    }


    @GetMapping
    public ResponseEntity<List<Planet>> getAllPlanets(){
        List<Planet> allPlanets = planetDAO.findAll();
        return ResponseEntity.ok(allPlanets);
    }

    @PostMapping
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet){
        return ResponseEntity.accepted().body(planetDAO.save(planet));
    }

}
