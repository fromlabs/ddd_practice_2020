package org.group4.dddpractice2020.lab2;

import java.util.List;
import java.util.function.Consumer;

public class ReserveHandler {
	private final EventStore eventStore;
	private final Consumer<List<Object>> publisher;

	public ReserveHandler(EventStore eventStore, Consumer<List<Object>> publisher) {
		this.eventStore = eventStore;
		this.publisher = publisher;
	}


	public void handle(ReserveCommand command) {
		ScreeningState screeningState = new ScreeningState(this.eventStore.getEvents());

		Screening screening = new Screening(screeningState, events -> {
			this.eventStore.addEvents(events);

			this.publisher.accept(events);
		});

		screening.reserveSeats(command.getCustomerId(), command.getReserveSeats());
	}
}
