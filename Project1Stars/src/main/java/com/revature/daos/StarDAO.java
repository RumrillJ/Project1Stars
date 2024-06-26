package com.revature.daos;

import com.revature.models.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarDAO extends JpaRepository<Star, Integer> {
    Star findByStarId(int starId);
}
