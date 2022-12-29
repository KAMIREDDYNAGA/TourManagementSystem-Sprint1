package com.sprint1.tourmanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.sprint1.tourmanagement.dto.ReservationDto;
import com.sprint1.tourmanagement.entity.Reservation;

public interface ReservationService {
	
	public Reservation newReservation(int userId,int tourId,String paymentInfo,LocalDate reservedDate,LocalDate paymentDate);
	public Reservation viewReservation(int userId);
	public ReservationDto modifyReservation(ReservationDto reservationDto);
	public List<Reservation> getAllReservation();
}
