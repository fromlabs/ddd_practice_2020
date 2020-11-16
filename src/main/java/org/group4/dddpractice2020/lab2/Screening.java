package org.group4.dddpractice2020.lab2;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Screening {

	private final ScreeningState screeningState;
	private final Consumer<List<Object>> publisher;

	public Screening(ScreeningState screeningState, Consumer<List<Object>> publisher) {
		this.screeningState = screeningState;
		this.publisher = publisher;
	}

	public void reserveSeats(String customerId, List<Seat> requestedSeats) {
		if (this.screeningState.getAvailableSeats().containsAll(requestedSeats)) {
			List<Object> events = requestedSeats.stream().map(seat -> new SeatReserved(customerId, seat)).collect(Collectors.toList());

			this.publisher.accept(events);
		} else {
			throw new UnreservableSeatException();
		}
	}
}
