package com.revature.daos;

import com.revature.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetDAO extends JpaRepository<Planet, Integer> {

}
