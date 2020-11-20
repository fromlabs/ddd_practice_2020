package org.group4.dddpractice2020.lab2;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Screening {

	private final ScreeningState screeningState;
	private final Consumer<List<Object>> publisher;

	public Screening(ScreeningState screeningState, Consumer<List<Object>> publisher) {
		this.screeningState = screeningState;
		this.publisher = publisher;
	}

	public void reserveSeats(String customerId, List<Seat> requestedSeats) {
		LocalDateTime reservationTime = LocalDateTime.now();

		if (reservationTime.isAfter(this.screeningState.getScreeningTime().getDateTime().minusMinutes(15))) {
			this.publisher.accept(Arrays.asList(new ReservationFailed()));
		} else if (this.screeningState.getAvailableSeats().containsAll(requestedSeats)) {
			this.publisher.accept(Arrays.asList(new SeatsReserved(customerId, requestedSeats.toArray(new Seat[0]))));
		} else {
			this.publisher.accept(Arrays.asList(new ReservationFailed()));
		}
	}
}
