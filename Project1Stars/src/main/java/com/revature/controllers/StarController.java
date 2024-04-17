package com.revature.controllers;

import com.revature.daos.StarDAO;
import com.revature.models.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stars")
public class StarController {

        private StarDAO starDAO;

        @Autowired
        public StarController(StarDAO starDAO){
            this.starDAO = starDAO;
        }

        //add Star method
        @PostMapping
        public ResponseEntity<Star> insertStar(@RequestBody Star star){
            Star s = starDAO.save(star);
            if(s == null) return ResponseEntity.badRequest().build();
            return ResponseEntity.status(201).body(s);
        }

        //get all Stars
        @GetMapping
        public ResponseEntity<List<Star>> getAllStars(){
            List<Star> stars = starDAO.findAll();
            return ResponseEntity.ok(stars);
        }

        //Get Star by IOD
        @GetMapping("/{starId}")
        public ResponseEntity<Star> getStarById(@PathVariable int starId){
            Star s = starDAO.findByStarId(starId);
            if(s == null) return ResponseEntity.status(404).build();
            return ResponseEntity.ok(s);
        }

        //Update Star Color
        @PutMapping("/{starId}")
        public ResponseEntity<Star> updateStarColor(@RequestBody Star star, @PathVariable int starId){
            Star s = starDAO.findByStarId(starId);
            if(s == null) return ResponseEntity.status(404).build();
            s.setStarColor(star.getStarColor());
            starDAO.save(s);
            return ResponseEntity.ok(s);
        }

        //Delete Star
        @DeleteMapping("/{starId}")
        public ResponseEntity<Star> deleteStar(@PathVariable int starId){
            starDAO.deleteById(starId);
            return ResponseEntity.status(204).build();
        }


}
