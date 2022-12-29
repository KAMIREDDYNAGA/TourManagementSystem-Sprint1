package com.sprint1.tourmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.tourmanagement.entity.Tour;
import com.sprint1.tourmanagement.service.TourService;

@RestController
public class TourController {

	@Autowired
	private TourService tourService;

	@PostMapping("NewTour")
	public ResponseEntity<String> saveTour(@Valid @RequestBody Tour tour) {
		String response = tourService.addTour(tour);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("ViewTours")
	public ResponseEntity<List<Tour>> allTours() {
		List<Tour> tours = tourService.displayTours();
		ResponseEntity<List<Tour>> responseEntity = new ResponseEntity<>(tours, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("FindTour/{tourId}")
	public ResponseEntity<Tour> getTourById(@PathVariable("tourId") int tourId) {
		Tour tour = tourService.findTour(tourId);
		ResponseEntity<Tour> responseEntity = new ResponseEntity<>(tour, HttpStatus.OK);
		return responseEntity;

	}

	@PutMapping("UpdateTourDetails")
	public ResponseEntity<Tour> modifyTour(@RequestBody Tour tour) {
		Tour tour1 = tourService.updateTour(tour);
		ResponseEntity<Tour> responseEntity = new ResponseEntity<>(tour1, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("Deletetour/{tourId}")
	public ResponseEntity<String> removeTour(@PathVariable("tourId") int tourId) {
		tourService.deleteTour(tourId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Tour with Id " + tourId + " Deleted",
				HttpStatus.OK);
		return responseEntity;
	}
}
