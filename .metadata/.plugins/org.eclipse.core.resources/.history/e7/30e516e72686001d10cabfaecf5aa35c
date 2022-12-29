package com.sprint1.tourmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint1.tourmanagement.entity.Reservation;
import com.sprint1.tourmanagement.entity.User;

public interface ReservationRepository extends JpaRepository<Reservation,Integer>{
	Reservation findByuser(User user);

}
