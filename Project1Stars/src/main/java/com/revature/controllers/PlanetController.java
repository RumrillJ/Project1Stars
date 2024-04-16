package com.revature.controllers;

import com.revature.daos.PlanetDAO;
import com.revature.daos.StarDAO;
import com.revature.models.Planet;
import com.revature.models.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private PlanetDAO planetDAO;
    private StarDAO starDAO;

    @Autowired
    public PlanetController(PlanetDAO planetDAO, StarDAO starDAO){
        this.planetDAO = planetDAO;
        this.starDAO =  starDAO;
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


    @PostMapping("/{starId}") //insert a planet to a star system
    public ResponseEntity<Planet> insertPlanetToStar(@RequestBody Planet planet, @PathVariable int starId){
        Star star = starDAO.findById(starId).get();
        planet.setStar(star);
        return ResponseEntity.status(201).body(planetDAO.save(planet));
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
