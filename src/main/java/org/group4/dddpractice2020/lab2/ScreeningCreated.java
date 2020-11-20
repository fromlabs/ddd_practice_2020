package org.group4.dddpractice2020.lab2;

import java.util.List;

public class ScreeningCreated {
	private final ScreeningTime screeningTime;
	private final List<Seat> seats;

	public ScreeningCreated(ScreeningTime screeningTime, List<Seat> seats) {
		this.screeningTime = screeningTime;
		this.seats = seats;
	}

	public ScreeningTime getScreeningTime() {
		return this.screeningTime;
	}

	public List<Seat> getSeats() {
		return this.seats;
	}
}
