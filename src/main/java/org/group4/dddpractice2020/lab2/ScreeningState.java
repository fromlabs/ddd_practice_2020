package org.group4.dddpractice2020.lab2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ScreeningState {
	private final Map<Seat, String> seats = new HashMap<>();
	private ScreeningTime screeningTime;

	public ScreeningState(List<Object> events) {
		for (Object event : events) {
			apply(event);
		}
	}

	private void apply(Object event) {
		if (event instanceof ScreeningCreated) {
			ScreeningCreated screeningCreated = (ScreeningCreated) event;
			this.screeningTime = screeningCreated.getScreeningTime();
			for (Seat seat : screeningCreated.getSeats()) {
				this.seats.put(seat, null);
			}
		} else if (event instanceof SeatsReserved) {
			SeatsReserved seatsReserved = (SeatsReserved) event;
			for (Seat seat : seatsReserved.getSeats()) {
				this.seats.put(seat, seatsReserved.getCustomerId());
			}
		} else if (event instanceof ReservationFailed) {
			//
		} else {
			throw new UnsupportedOperationException("Event: " + event);
		}
	}

	public ScreeningTime getScreeningTime() {
		return this.screeningTime;
	}

	public Set<Seat> getAvailableSeats() {
		return this.seats.entrySet().stream().filter(seatStringEntry -> seatStringEntry.getValue() == null).map(seatStringEntry -> seatStringEntry.getKey()).collect(Collectors.toSet());
	}
}
