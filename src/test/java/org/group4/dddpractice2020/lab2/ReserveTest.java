package org.group4.dddpractice2020.lab2;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
class ReserveTest {

	private EventStore eventStore;
	private List<Object> publishedEvents;

	@Test
	void reserveOk() {
		given(Arrays.asList(new SeatingCreated(new Seat(1, 1)), new SeatingCreated(new Seat(1, 2)), new SeatingCreated(new Seat(1, 3)), new SeatingCreated(new Seat(1, 4))));

		when(new ReserveCommand("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));

		thenExpect(Arrays.asList(new SeatReserved("roby", new Seat(1, 2)), new SeatReserved("roby", new Seat(1, 3))));
	}

	@Test
	void reserveNotAvailableSeats() {
		given(Arrays.asList(new SeatingCreated(new Seat(1, 1)), new SeatingCreated(new Seat(1, 2)),
				new SeatingCreated(new Seat(1, 3)), new SeatingCreated(new Seat(1, 4)),
				new SeatReserved("roby", new Seat(1, 2))
		));

		Assertions.assertThrows(UnreservableSeatException.class, () -> when(new ReserveCommand("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3)))));
	}

	private void given(List<Object> events) {
		this.publishedEvents = new ArrayList<>();

		this.eventStore = new EventStore();

		this.eventStore.addEvents(events);
	}

	private void when(ReserveCommand command) {
		ReserveHandler handler = new ReserveHandler(this.eventStore, (events) -> {
			this.publishedEvents.addAll(events);
		});

		handler.handle(command);
	}

	private void thenExpect(List<Object> events) {
		Assertions.assertTrue(this.publishedEvents.containsAll(events));
	}
}