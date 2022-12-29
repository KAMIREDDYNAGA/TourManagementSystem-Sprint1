package com.sprint1.tourmanagement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tour_info")
public class Tour {
	
	@Id
	@Column(name="tourId")
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator="tourGenerator"
			)
	
	@SequenceGenerator(
			name="tourGenerator",
			sequenceName="tourSequence",
			initialValue=51,
			allocationSize=1
			)
	private int tourId;
	
	@Column(name="tourName",nullable=false,unique=true)
	private String tourName;
	
	@Column(name="tourLocation",nullable=false)
	private String tourLocation;
	
	@Column(name="tourDetails")
	private String tourDetails;
	
	@Column(name="tourPrice")
	private double tourPrice;
	
	@Column(name="tourStartDate")
	private LocalDate tourStartDate;
	
	@Column(name="tourEndDate")
	private LocalDate tourEndDate;
	
	@Column(name="tourisActive")
	private boolean tourIsActive;

	public int getTourId() {
		return tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public String getTourLocation() {
		return tourLocation;
	}

	public void setTourLocation(String tourLocation) {
		this.tourLocation = tourLocation;
	}

	public String getTourDetails() {
		return tourDetails;
	}

	public void setTourDetails(String tourDetails) {
		this.tourDetails = tourDetails;
	}

	public double getTourPrice() {
		return tourPrice;
	}

	public void setTourPrice(double tourPrice) {
		this.tourPrice = tourPrice;
	}

	public LocalDate getTourStartDate() {
		return tourStartDate;
	}

	public void setTourStartDate(LocalDate tourStartDate) {
		this.tourStartDate = tourStartDate;
	}

	public LocalDate getTourEndDate() {
		return tourEndDate;
	}

	public void setTourEndDate(LocalDate tourEndDate) {
		this.tourEndDate = tourEndDate;
	}

	public boolean isTourIsActive() {
		return tourIsActive;
	}

	public void setTourIsActive(boolean tourIsActive) {
		this.tourIsActive = tourIsActive;
	}
}
