package com.sprint1.tourmanagement.exception;

public class TourNotFoundException extends RuntimeException{
	public TourNotFoundException(String msg) {
		super(msg);
	}
}
