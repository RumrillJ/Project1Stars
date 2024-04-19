package com.revature.controllers;

import com.revature.daos.PlanetDAO;
import com.revature.daos.StarDAO;
import com.revature.models.Planet;
import com.revature.models.Star;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
        Optional<Planet> p = planetDAO.findById(planetId);
        if (p.isEmpty()){ // if planet is null
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(p.get());
    }


    @PostMapping("/{starId}") //insert a planet to a star system
    public ResponseEntity<Planet> insertPlanetToStar(@RequestBody Planet planet, @PathVariable int starId){
        Optional<Star> star = starDAO.findById(starId);
        if (star.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        planet.setStar(star.get());
        return ResponseEntity.status(201).body(planetDAO.save(planet));
    }

    @PutMapping //entire update...No need to specify Id because save will look for the
    // planets ID already and either edit that one or create a new one if the Id is new
    public ResponseEntity<Planet> updatePlanet(@RequestBody Planet planet){
        //make sure the starId is not changed
        //since this is a put request, we can assume the planet and star already exist
        Planet p = planetDAO.findById(planet.getPlanetId()).get();
        Star s = starDAO.findById(p.getStar().getStarId()).get();
        //try to check if the planet has a star
        if (planet.getStar() == null){ //if the planet passed in from the body does not specify a star, use the star it originally had
            planet.setStar(s);
        }
        //This will update the star with everything that the body has without the need to specify a star (unless you want to)
        return ResponseEntity.ok(planetDAO.save(planet));
    }

    @PatchMapping("/{planetId}") //update only the mass field
    public ResponseEntity<Planet> updatePlanetMass(@RequestBody int mass, @PathVariable int planetId){
        Optional<Planet> p = planetDAO.findById(planetId);
        if (p.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Planet planet = p.get(); // the found planet in our DB that needs to have a new mass
        planet.setMass(mass);
        return ResponseEntity.ok(planetDAO.save(planet));
    }

    @DeleteMapping("{planetId}")
    public ResponseEntity<String> deletePlanet(@PathVariable int planetId){
        Optional<Planet> p = planetDAO.findById(planetId);
        if (p.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        planetDAO.deleteById(planetId);
        Planet planet = p.get();
        return ResponseEntity.status(204).body("Planet " + planet.getName() + " has been deleted");
    }
}
