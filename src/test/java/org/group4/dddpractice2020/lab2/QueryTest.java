package org.group4.dddpractice2020.lab2;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Log4j2
class QueryTest {

	private EventStore eventStore;
	private Object publishedModel;

	@Test
	void reservedModel() {
		LocalDateTime screeningDateTime = LocalDateTime.now().minusMinutes(100);

		given(new ScreeningCreated(new ScreeningTime(screeningDateTime.toLocalDate(), screeningDateTime.getHour(), screeningDateTime.getMinute()),
						Arrays.asList(new Seat(1, 1), new Seat(1, 2),
								new Seat(1, 3), new Seat(1, 4))),
				new SeatsReserved("roby", new Seat(1, 2), new Seat(1, 3))
		);

		query(new CustomerReservationQuery("roby"));

		thenExpect(new CustomerReservationResponse("roby", new Seat(1, 2), new Seat(1, 3)));
	}

	private void given(Object... events) {
		this.publishedEvents = new ArrayList<>();

		this.eventStore.addEvents(Arrays.asList(events));
	}

	private void query(CustomerReservationQuery query) {
		this.publishedModel = new ReadModel(publishedEvents);

		QueryHandler handler = new QueryHandler(this.eventStore);

		this.publishedModel = handler.query(query);
	}

	private void thenExpect(Object model) {
		Assertions.assertEquals(model, this.publishedModel);
	}
}