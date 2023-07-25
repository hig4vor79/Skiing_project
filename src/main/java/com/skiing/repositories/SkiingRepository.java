package com.skiing.repositories;

import com.skiing.models.entities.Skiing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiingRepository extends JpaRepository<Skiing, String> {
}
