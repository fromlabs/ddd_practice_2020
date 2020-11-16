package org.group4.dddpractice2020.lab2;

import java.util.List;

public class ReserveCommand {
	private final String customerId;
	private final List<Seat> reserveSeats;

	public ReserveCommand(String customerId, List<Seat> reserveSeats) {
		this.customerId = customerId;
		this.reserveSeats = reserveSeats;
	}


	public String getCustomerId() {
		return this.customerId;
	}

	public List<Seat> getReserveSeats() {
		return this.reserveSeats;
	}
}
