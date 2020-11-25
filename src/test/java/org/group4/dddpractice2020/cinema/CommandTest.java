package org.group4.dddpractice2020.cinema;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import lombok.extern.log4j.Log4j2;
import org.group4.dddpractice2020.cinema.application.command.ReserveCommand;
import org.group4.dddpractice2020.cinema.common.BaseCinemaTest;
import org.group4.dddpractice2020.cinema.domain.event.ReservationFailed;
import org.group4.dddpractice2020.cinema.domain.event.ScreeningCreated;
import org.group4.dddpractice2020.cinema.domain.event.SeatsReserved;
import org.group4.dddpractice2020.cinema.domain.valuetype.ScreeningTime;
import org.group4.dddpractice2020.cinema.domain.valuetype.Seat;
import org.junit.jupiter.api.Test;

@Log4j2
class CommandTest extends BaseCinemaTest {

  @Test
  void reserveOk() {
    LocalDateTime screeningDateTime = LocalDateTime.now().plusMinutes(100);

    given(
        new ScreeningCreated(
            new ScreeningTime(
                screeningDateTime.toLocalDate(),
                screeningDateTime.getHour(),
                screeningDateTime.getMinute()),
            Arrays.asList(new Seat(1, 1), new Seat(1, 2), new Seat(1, 3), new Seat(1, 4))));

    whenCommand(new ReserveCommand("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));

    thenExpectEvents(new SeatsReserved("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));
  }

  @Test
  void reserveNotAvailableSeats() {
    LocalDateTime screeningDateTime = LocalDateTime.now().minusMinutes(100);

    given(
        new ScreeningCreated(
            new ScreeningTime(
                screeningDateTime.toLocalDate(),
                screeningDateTime.getHour(),
                screeningDateTime.getMinute()),
            Arrays.asList(new Seat(1, 1), new Seat(1, 2), new Seat(1, 3), new Seat(1, 4))),
        new SeatsReserved("rony", Collections.singletonList(new Seat(1, 2))));

    whenCommand(new ReserveCommand("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));

    thenExpectEvents(new ReservationFailed());
  }

  @Test
  void reserveLate() {
    LocalDateTime screeningDateTime = LocalDateTime.now().plusMinutes(10);

    given(
        new ScreeningCreated(
            new ScreeningTime(
                screeningDateTime.toLocalDate(),
                screeningDateTime.getHour(),
                screeningDateTime.getMinute()),
            Arrays.asList(new Seat(1, 1), new Seat(1, 2), new Seat(1, 3), new Seat(1, 4))));

    whenCommand(new ReserveCommand("roby", Arrays.asList(new Seat(1, 2), new Seat(1, 3))));

    thenExpectEvents(new ReservationFailed());
  }
}
