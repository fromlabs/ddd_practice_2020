package org.group4.dddpractice2020.cinema;

import lombok.extern.log4j.Log4j2;
import org.group4.dddpractice2020.cinema.common.BaseCinemaTest;
import org.junit.jupiter.api.Test;

@Log4j2
class CommandTest extends BaseCinemaTest {

  @Test
  void reserveOk() {
    given(screeningCreated(SCREENING_TIME_IN_100));

    whenCommand(reserveSeats(ROBY, SEAT_1_2, SEAT_1_3));

    thenExpectEvents(seatsReserved(ROBY, SEAT_1_2, SEAT_1_3));
  }

  @Test
  void reserveNotAvailableSeats() {
    given(screeningCreated(SCREENING_TIME_IN_100), seatsReserved(RONY, SEAT_1_2));

    whenCommand(reserveSeats(ROBY, SEAT_1_2, SEAT_1_3));

    thenExpectEvents(RESERVATION_FAILED);
  }

  @Test
  void reserveLate() {
    given(screeningCreated(SCREENING_TIME_IN_10));

    whenCommand(reserveSeats(ROBY, SEAT_1_2, SEAT_1_3));

    thenExpectEvents(RESERVATION_FAILED);
  }
}
