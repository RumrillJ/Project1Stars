package com.revature.controllers;

import com.revature.daos.PlanetDAO;
import com.revature.models.Planet;
import org.apache.coyote.Response;
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

    @GetMapping("{planetId}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable int planetId){
        return ResponseEntity.ok(planetDAO.findById(planetId).get());
    }


    @PostMapping
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet){
        return ResponseEntity.accepted().body(planetDAO.save(planet));
    }

    @PutMapping
    public ResponseEntity<Planet> updatePlanet(@RequestBody Planet planet){
        return ResponseEntity.ok(planetDAO.save(planet));
    }

    @DeleteMapping("{planetId}")
    public ResponseEntity<String> deletePlanet(@PathVariable int planetId){
        Planet p = planetDAO.findById(planetId).get();
        planetDAO.deleteById(planetId);
        return ResponseEntity.accepted().body("Planet " + p.getName() + " has been deleted");
    }
}
