package org.group4.dddpractice2020.lab2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ScreeningState {
	private final Map<Seat, String> seats = new HashMap<>();

	public ScreeningState(List<Object> events) {
		for (Object event : events) {
			apply(event);
		}
	}

	private void apply(Object event) {
		if (event instanceof SeatingCreated) {
			this.seats.put(((SeatingCreated) event).getSeat(), null);
		} else if (event instanceof SeatReserved) {
			this.seats.put(((SeatReserved) event).getSeat(), ((SeatReserved) event).getCustomerId());
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public Set<Seat> getAvailableSeats() {
		return this.seats.entrySet().stream().filter(seatStringEntry -> seatStringEntry.getValue() == null).map(seatStringEntry -> seatStringEntry.getKey()).collect(Collectors.toSet());
	}
}
