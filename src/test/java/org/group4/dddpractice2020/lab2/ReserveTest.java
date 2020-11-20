package org.group4.dddpractice2020.lab2;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
class ReserveTest {

	private EventStore eventStore;
	private List<Object> publishedEvents;

	@Test
	void reserveOk() {
		LocalDateTime screeningDateTime = LocalDateTime.now().minusMinutes(100);

		given(new ScreeningCreated(new ScreeningTime(screeningDateTime.toLocalDate(), screeningDateTime.getHour(), screeningDateTime.getMinute()),
				Arrays.asList(new Seat(1, 1), new Seat(1, 2),
						new Seat(1, 3), new Seat(1, 4)))
		);

		when(new ReserveCommand("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));

		thenExpect(new SeatsReserved("roby", new Seat(1, 2), new Seat(1, 3)));
	}

	@Test
	void reserveNotAvailableSeats() {
		LocalDateTime screeningDateTime = LocalDateTime.now().minusMinutes(100);

		given(new ScreeningCreated(new ScreeningTime(screeningDateTime.toLocalDate(), screeningDateTime.getHour(), screeningDateTime.getMinute()),
				Arrays.asList(new Seat(1, 1), new Seat(1, 2),
						new Seat(1, 3), new Seat(1, 4))), new SeatsReserved("rony", new Seat(1, 2)));

		when(new ReserveCommand("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));

		thenExpect(new ReservationFailed());
	}

	@Test
	void reserveLate() {
		LocalDateTime screeningDateTime = LocalDateTime.now().plusMinutes(10);

		given(new ScreeningCreated(new ScreeningTime(screeningDateTime.toLocalDate(), screeningDateTime.getHour(), screeningDateTime.getMinute()),
				Arrays.asList(new Seat(1, 1), new Seat(1, 2),
						new Seat(1, 3), new Seat(1, 4)))
		);

		when(new ReserveCommand("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));

		thenExpect(new ReservationFailed());
	}

	private void given(Object... events) {
		this.publishedEvents = new ArrayList<>();

		this.eventStore = new EventStore();

		this.eventStore.addEvents(Arrays.asList(events));
	}

	private void when(ReserveCommand command) {
		ReserveHandler handler = new ReserveHandler(this.eventStore, (events) -> {
			this.publishedEvents.addAll(events);
		});

		handler.handle(command);
	}

	private void thenExpect(Object... events) {
		Assertions.assertTrue(this.publishedEvents.containsAll(Arrays.asList(events)));
	}
}