package org.group4.dddpractice2020.cinema;

import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.group4.dddpractice2020.cinema.application.command.CinemaCommandHandler;
import org.group4.dddpractice2020.cinema.application.command.ReserveCommand;
import org.group4.dddpractice2020.cinema.application.query.CinemaQueryHandler;
import org.group4.dddpractice2020.cinema.application.query.CustomerReservationQuery;
import org.group4.dddpractice2020.cinema.domain.event.ScreeningCreated;
import org.group4.dddpractice2020.cinema.domain.valuetype.ScreeningTime;
import org.group4.dddpractice2020.cinema.domain.valuetype.Seat;
import org.group4.dddpractice2020.core.DomainCommandHandler;
import org.group4.dddpractice2020.core.DomainQueryHandler;
import org.group4.dddpractice2020.core.EventStore;
import org.group4.dddpractice2020.core.EventStoreImpl;
import org.junit.jupiter.api.Test;

@Log4j2
class ExperimentTest {

  @Test
  void reserveOk() {
    LocalDateTime screeningDateTime = LocalDateTime.now().plusMinutes(100);

    EventStore eventStore =
        new EventStoreImpl(
            new ScreeningCreated(
                new ScreeningTime(
                    screeningDateTime.toLocalDate(),
                    screeningDateTime.getHour(),
                    screeningDateTime.getMinute()),
                Arrays.asList(new Seat(1, 1), new Seat(1, 2), new Seat(1, 3), new Seat(1, 4))));

    DomainCommandHandler commandHandler =
        new CinemaCommandHandler(
            eventStore,
            domainEvent -> {
              log.info("Published {}", domainEvent);
            });

    DomainQueryHandler queryHandler =
        new CinemaQueryHandler(
            eventStore,
            response -> {
              log.info("Queried {}", response);
            });

    queryHandler.handle(new CustomerReservationQuery("roby"));

    commandHandler.handle(
        new ReserveCommand("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));

    queryHandler.handle(new CustomerReservationQuery("roby"));
  }
}
