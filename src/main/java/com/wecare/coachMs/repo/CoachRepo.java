package com.wecare.coachMs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecare.coachMs.entity.CoachEntity;

@Repository
public interface CoachRepo extends JpaRepository<CoachEntity, String>{
	
	List<CoachEntity> findByName(String name);
}
