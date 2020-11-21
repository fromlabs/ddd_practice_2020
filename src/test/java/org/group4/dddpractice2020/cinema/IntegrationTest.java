package org.group4.dddpractice2020.cinema;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;
import lombok.extern.log4j.Log4j2;
import org.group4.dddpractice2020.cinema.application.command.CinemaCommandHandler;
import org.group4.dddpractice2020.cinema.application.command.ReserveCommand;
import org.group4.dddpractice2020.cinema.application.query.CinemaQueryHandler;
import org.group4.dddpractice2020.cinema.application.query.CustomerReservationQuery;
import org.group4.dddpractice2020.cinema.application.query.CustomerReservationResponse;
import org.group4.dddpractice2020.cinema.domain.event.ScreeningCreated;
import org.group4.dddpractice2020.cinema.domain.event.SeatsReserved;
import org.group4.dddpractice2020.cinema.domain.valuetype.Reservation;
import org.group4.dddpractice2020.cinema.domain.valuetype.ScreeningTime;
import org.group4.dddpractice2020.cinema.domain.valuetype.Seat;
import org.group4.dddpractice2020.core.BaseTest;
import org.group4.dddpractice2020.core.DomainCommandHandler;
import org.group4.dddpractice2020.core.DomainEvent;
import org.group4.dddpractice2020.core.DomainQueryHandler;
import org.group4.dddpractice2020.core.DomainQueryResponse;
import org.group4.dddpractice2020.core.EventStore;
import org.junit.jupiter.api.Test;

@Log4j2
class IntegrationTest extends BaseTest {

  @Override
  protected DomainCommandHandler commandHandlerFactory(
      EventStore eventStore, Consumer<DomainEvent> publisher) {
    return new CinemaCommandHandler(eventStore, publisher);
  }

  @Override
  protected DomainQueryHandler queryHandlerFactory(
      EventStore eventStore, Consumer<DomainQueryResponse> responder) {
    return new CinemaQueryHandler(eventStore, responder);
  }

  @Test
  void reserveOk() {
    // given
    LocalDateTime screeningDateTime = LocalDateTime.now().plusMinutes(100);
    given(
        new ScreeningCreated(
            new ScreeningTime(
                screeningDateTime.toLocalDate(),
                screeningDateTime.getHour(),
                screeningDateTime.getMinute()),
            Arrays.asList(new Seat(1, 1), new Seat(1, 2), new Seat(1, 3), new Seat(1, 4))));

    // query reservations
    whenQuery(new CustomerReservationQuery("roby"));
    thenExpectResponse(new CustomerReservationResponse(Collections.emptyList()));

    // reserve command
    whenCommand(new ReserveCommand("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));
    thenExpectEvents(new SeatsReserved("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));

    // query reservations
    whenQuery(new CustomerReservationQuery("roby"));
    thenExpectResponse(
        new CustomerReservationResponse(
            Collections.singletonList(
                new Reservation(Arrays.asList(new Seat(1, 2), new Seat(1, 3))))));
  }
}