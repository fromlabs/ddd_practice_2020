package org.group4.dddpractice2020.cinema;

import lombok.extern.log4j.Log4j2;
import org.group4.dddpractice2020.cinema.common.BaseCinemaTest;
import org.junit.jupiter.api.Test;

@Log4j2
class QueryTest extends BaseCinemaTest {

  @Test
  void reserveOk() {
    given(screeningCreated(SCREENING_TIME_IN_100), seatsReserved(ROBY, SEAT_1_2, SEAT_1_3));

    whenQuery(queryReservationsOf(ROBY));

    thenExpectResponse(responseReservationsOf(SEAT_1_2, SEAT_1_3));
  }
}
