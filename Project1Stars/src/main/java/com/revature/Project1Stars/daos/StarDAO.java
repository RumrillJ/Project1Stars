package com.revature.Project1Stars.daos;

import com.revature.Project1Stars.models.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarDAO extends JpaRepository<Star, Integer> {
    Star findByStarId(int starId);

}
