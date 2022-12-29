package com.sprint1.tourmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.tourmanagement.entity.Tour;
import com.sprint1.tourmanagement.exception.TourNotFoundException;
import com.sprint1.tourmanagement.repository.TourRepository;

@Service
public class TourServiceImpl implements TourService {

	@Autowired
	private TourRepository tourRepository;

	//Creating New Tour
	@Override
	public String addTour(Tour tour) {
		
		//first find is the username existing
		String tourName = tour.getTourName();
		Tour tour1 = tourRepository.findBytourName(tourName);
		if (tour1 != null) {
			return "Tourname already Existed";
		}
		//save tour
		tourRepository.save(tour);
		return "Tour added Successfully";
	}

	//Viewing all the tours
	@Override
	public List<Tour> displayTours() {
		List<Tour> tours = tourRepository.findAll();
		return tours;
	}

	
	//Modifying the Tour
	@Override
	public Tour updateTour(Tour tour) {
		//first find it 
		Optional<Tour> tour1 = tourRepository.findById(tour.getTourId());
		if (tour1.isEmpty()) {
			throw new TourNotFoundException("Tour with id " + tour.getTourId() + " is not existed");
		}
		//change the values and save
		Tour tour2 = tourRepository.save(tour);
		return tour2;
	}

	//delete Tour
	@Override
	public void deleteTour(int tourId) {
		//first find the tour by the tour id
		Optional<Tour> tour = tourRepository.findById(tourId);
		if (tour.isEmpty()) {
			throw new TourNotFoundException("Tour with id " + tourId + " is not existed");
		}
		//delete the tour
		tourRepository.deleteById(tourId);
	}

	//Finding a tour with tourId
	@Override
	public Tour findTour(int tourId) {
		Optional<Tour> optionalTour = tourRepository.findById(tourId);
		if (optionalTour.isEmpty()) {
			throw new TourNotFoundException("There is no Tour with the Id:" + tourId);
		}
		Tour tour = optionalTour.get();
		return tour;
	}

}
