package com.wecare.coachMs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecare.coachMs.entity.CoachEntity;

@Repository
public interface CoachRepo extends JpaRepository<CoachEntity, String>{

}
