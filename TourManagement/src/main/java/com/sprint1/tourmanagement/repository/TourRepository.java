package com.sprint1.tourmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.tourmanagement.entity.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
	public Tour findBytourName(String tourName);

}
