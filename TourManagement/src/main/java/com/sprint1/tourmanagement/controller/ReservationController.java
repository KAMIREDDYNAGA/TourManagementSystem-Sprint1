package com.sprint1.tourmanagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.tourmanagement.entity.Reservation;
import com.sprint1.tourmanagement.service.ReservationService;
import com.sprint1.tourmanagement.service.UserService;

@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private UserService userService;

	@PostMapping("NewReservation/{userId}/{tourId}/{paymentInfo}/{reservedDate}/{paymentDate}")
	public ResponseEntity<String> reserveTour(@PathVariable("userId") int userId, @PathVariable("tourId") int tourId,
			@PathVariable("paymentInfo") String paymentInfo, @PathVariable("reservedDate") String rDate,
			@PathVariable("paymentDate") String pDate) {
		LocalDate reservedDate = LocalDate.parse(rDate);
		LocalDate paymentDate = LocalDate.parse(pDate);
		String response = reservationService.newReservation(userId, tourId, paymentInfo.toLowerCase(), reservedDate,
				paymentDate);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("ViewAllReservations/{userId}/{password}")
	public ResponseEntity<List<Reservation>> fetchAll(@PathVariable("userId") String userId,
			@PathVariable("password") String password) {
		userService.validLogin(userId, password);
		List<Reservation> list = reservationService.getAllReservation();
		ResponseEntity<List<Reservation>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("ViewReservation/{userId}")
	public ResponseEntity<List<Reservation>> viewReservation(@PathVariable("userId") int userId) {
		List<Reservation> reservation = reservationService.viewReservation(userId);
		ResponseEntity<List<Reservation>> responseEntity = new ResponseEntity<>(reservation, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("ModifyReservation")
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
		Reservation ureservation = reservationService.modifyReservation(reservation);
		ResponseEntity<Reservation> responseEntity = new ResponseEntity<>(ureservation, HttpStatus.OK);
		return responseEntity;
	}

}
