package org.group4.dddpractice2020.lab2;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SeatReserved {
	private final String customerId;
	private final Seat seat;

	public SeatReserved(String customerId, Seat seat) {
		this.customerId = customerId;
		this.seat = seat;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public Seat getSeat() {
		return this.seat;
	}
}
