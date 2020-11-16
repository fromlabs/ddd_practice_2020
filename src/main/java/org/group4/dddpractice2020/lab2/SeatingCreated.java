package org.group4.dddpractice2020.lab2;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SeatingCreated {
	private final Seat seat;

	public SeatingCreated(Seat seat) {
		this.seat = seat;
	}

	public Seat getSeat() {
		return this.seat;
	}
}
