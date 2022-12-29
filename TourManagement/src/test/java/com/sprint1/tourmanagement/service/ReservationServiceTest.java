package com.sprint1.tourmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint1.tourmanagement.entity.Address;
import com.sprint1.tourmanagement.entity.Reservation;
import com.sprint1.tourmanagement.entity.Tour;
import com.sprint1.tourmanagement.entity.User;
import com.sprint1.tourmanagement.repository.ReservationRepository;
import com.sprint1.tourmanagement.repository.TourRepository;
import com.sprint1.tourmanagement.repository.UserRepository;

@SpringBootTest
public class ReservationServiceTest {
	
	@InjectMocks
	ReservationServiceImpl reservationService=new ReservationServiceImpl();
	
	@Mock
	private ReservationRepository reservationRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private TourRepository tourRepository;
	
	
	
	@Test
	public void displayAllReservations() {
		List<Reservation> reservations=new ArrayList<>();
		
		Reservation reservation=new Reservation();
		reservation.setReservationId(1001);
		reservation.setReservedDate(LocalDate.of(2022, 12, 01));
		reservation.setPaymentDate(LocalDate.of(2022, 12, 01));
		reservation.setPaymentInfo("cash");
		reservation.setPaymentStatus("completed");
		
		Tour tour=new Tour();
		tour.setTourId(123);
		tour.setTourName("VSKP");
		tour.setTourLocation("VSK");
		tour.setTourDetails("Beach");
		tour.setTourPrice("9999");
		tour.setTourStartDate(LocalDate.of(2022, 12, 12));
		tour.setTourEndDate(LocalDate.of(2022, 12, 15));
		tour.setTourIsActive(true);
		
		User user=new User();
		user.setUserId(7);
        user.setUserName("maheshwari");
        user.setUserContact("9542802054");
        user.setUserUserId("ABIOP");
        user.setUserPassword("Maheshwari@1234");
        
        Address address1=new Address();
        address1.setAddressId(60);
        address1.setCity("vizagggg");
        address1.setDno("2-12a");
        address1.setLocality("patnam");
        address1.setState("Ap");
        address1.setPincode(541113);
        user.setAddress(address1);
        
		reservation.setTour(tour);
		reservation.setUser(user);
		
		Reservation reservation1=new Reservation();
		reservation1.setReservationId(1002);
		reservation1.setReservedDate(LocalDate.of(2022, 11, 01));
		reservation1.setPaymentDate(LocalDate.of(2022, 11, 01));
		reservation1.setPaymentInfo("cash");
		reservation1.setPaymentStatus("completed");
		reservation.setTour(tour);
		reservation.setUser(user);
		
		reservations.add(reservation1);
		reservations.add(reservation);
		
		when(reservationRepository.findAll()).thenReturn(reservations);
		List<Reservation> reservationList=reservationService.getAllReservation();
		
		assertEquals(reservations.size(),reservationList.size());
	}
	
	@Test
	public void viewReservation() {
		Reservation reservation=new Reservation();
		reservation.setReservationId(1003);
		reservation.setReservedDate(LocalDate.of(2022, 10, 01));
		reservation.setPaymentDate(LocalDate.of(2022, 10, 01));
		reservation.setPaymentInfo("cash");
		reservation.setPaymentStatus("completed");
		
		Tour tour=new Tour();
		tour.setTourId(123);
		tour.setTourName("VSKP");
		tour.setTourLocation("VSK");
		tour.setTourDetails("Beach");
		tour.setTourPrice("9999");
		tour.setTourStartDate(LocalDate.of(2022, 12, 12));
		tour.setTourEndDate(LocalDate.of(2022, 12, 15));
		tour.setTourIsActive(true);
		
		User user=new User();
		user.setUserId(7);
        user.setUserName("maheshwari");
        user.setUserContact("9812309538");
        user.setUserUserId("ABIOP");
        user.setUserPassword("Maheshwari@1234");
        
        Address address1=new Address();
        address1.setAddressId(60);
        address1.setCity("vizagggg");
        address1.setDno("2-12a");
        address1.setLocality("patnam");
        address1.setState("Ap");
        address1.setPincode(541113);
        user.setAddress(address1);
        
		reservation.setTour(tour);
		reservation.setUser(user);
		
		Optional<Reservation> optionalReservation=Optional.of(reservation);
		
		when(reservationRepository.findById(1003)).thenReturn(optionalReservation);
		
		Reservation reservationObj=reservationService.viewReservationById(1003);
		assertEquals(1003,reservationObj.getReservationId());
		assertEquals(LocalDate.of(2022, 10, 01),reservationObj.getPaymentDate());
		assertEquals(LocalDate.of(2022, 10, 01),reservationObj.getReservedDate());
		assertEquals("cash",reservationObj.getPaymentInfo());
		assertEquals("completed",reservationObj.getPaymentStatus());
	}
	
	/*@Test
	public void addReservation() {
		Reservation reservation=new Reservation();
		reservation.setReservationId(1001);
		reservation.setReservedDate(LocalDate.of(2022, 12, 01));
		reservation.setPaymentDate(LocalDate.of(2022, 12, 01));
		reservation.setPaymentInfo("cash");
		reservation.setPaymentStatus("completed");
		
		Tour tour=new Tour();
		tour.setTourId(123);
		tour.setTourName("VSKP");
		tour.setTourLocation("VSK");
		tour.setTourDetails("Beach");
		tour.setTourPrice(9999);
		tour.setTourStartDate(LocalDate.of(2022, 12, 12));
		tour.setTourEndDate(LocalDate.of(2022, 12, 15));
		tour.setTourIsActive(true);
		
		User user=new User();
		user.setUserId(7);
        user.setUserName("maheshwari");
        user.setUserContact(984554321);
        user.setUserUserId("ABIOP");
        user.setUserPassword("Maheshwari@1234");
        
        Address address1=new Address();
        address1.setAddressId(60);
        address1.setCity("vizagggg");
        address1.setDno("2-12a");
        address1.setLocality("patnam");
        address1.setState("Ap");
        address1.setPincode(541113);
        user.setAddress(address1);
        
		reservation.setTour(tour);
		reservation.setUser(user);
		
		when(reservationRepository.save(reservation)).thenReturn(reservation);
		
		Reservation newReservation=reservationService.newReservation(7, 123, "cash", LocalDate.of(2022, 12, 01),LocalDate.of(2022, 12, 01));
		assertEquals(1001,newReservation.getReservationId());
		assertEquals(LocalDate.of(2022, 12, 01),newReservation.getReservedDate());
		assertEquals(LocalDate.of(2022, 12, 01),newReservation.getPaymentDate());
		assertEquals("cash",newReservation.getPaymentInfo());
	}*/
	
	@Test
	public void deleteReservation() {
		Reservation reservation=new Reservation();
		reservation.setReservationId(1003);
		reservation.setReservedDate(LocalDate.of(2022, 10, 01));
		reservation.setPaymentDate(LocalDate.of(2022, 10, 01));
		reservation.setPaymentInfo("cash");
		reservation.setPaymentStatus("completed");
		
		Tour tour=new Tour();
		tour.setTourId(123);
		tour.setTourName("VSKP");
		tour.setTourLocation("VSK");
		tour.setTourDetails("Beach");
		tour.setTourPrice("9999");
		tour.setTourStartDate(LocalDate.of(2022, 12, 12));
		tour.setTourEndDate(LocalDate.of(2022, 12, 15));
		tour.setTourIsActive(true);
		
		User user=new User();
		user.setUserId(7);
        user.setUserName("maheshwari");
        user.setUserContact("9653429013");
        user.setUserUserId("ABIOP");
        user.setUserPassword("Maheshwari@1234");
        
        Address address1=new Address();
        address1.setAddressId(60);
        address1.setCity("vizagggg");
        address1.setDno("2-12a");
        address1.setLocality("patnam");
        address1.setState("Ap");
        address1.setPincode(541113);
        user.setAddress(address1);
        
		reservation.setTour(tour);
		reservation.setUser(user);
		
		Optional<Reservation> optionalReservation=Optional.of(reservation);
		when(reservationRepository.findById(123)).thenReturn(optionalReservation);
		doNothing().when(reservationRepository).deleteById(123);
		
		reservationService.deleteReservation(123);
		verify(reservationRepository,times(1)).findById(123);
		verify(reservationRepository,times(1)).deleteById(123);
	}
}