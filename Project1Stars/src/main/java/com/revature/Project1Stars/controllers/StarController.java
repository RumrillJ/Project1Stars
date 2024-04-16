package com.revature.Project1Stars.controllers;

import com.revature.Project1Stars.daos.StarDAO;
import com.revature.Project1Stars.models.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stars")
public class StarController {

        private StarDAO starDAO;

        @Autowired
        public StarController(StarDAO starDAO){
            this.starDAO = starDAO;
        }

        @PostMapping
        public ResponseEntity<Star> insertStar(@RequestBody Star star){
            Star s = starDAO.save(star);
            if(s == null) return ResponseEntity.badRequest().build();
            return ResponseEntity.status(201).body(s);
        }
}
