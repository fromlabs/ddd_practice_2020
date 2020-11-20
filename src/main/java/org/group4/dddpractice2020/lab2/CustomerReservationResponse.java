package org.group4.dddpractice2020.lab2;

import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode
public class CustomerReservationResponse {
	private final String customerId;
	private final List<Seat> seats;

	public CustomerReservationResponse(String customerId, Seat... seats) {
		this.customerId = customerId;
		this.seats = Arrays.asList(seats);
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public List<Seat> getSeats() {
		return this.seats;
	}
}
