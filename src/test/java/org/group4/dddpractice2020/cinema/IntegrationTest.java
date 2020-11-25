package org.group4.dddpractice2020.cinema;

import lombok.extern.log4j.Log4j2;
import org.group4.dddpractice2020.cinema.common.BaseCinemaTest;
import org.junit.jupiter.api.Test;

@Log4j2
class IntegrationTest extends BaseCinemaTest {

  @Test
  void reserveOk() {
    // given
    given(screeningCreated(SCREENING_TIME_IN_100));

    // query reservations
    whenQuery(queryReservationsOf(ROBY));
    thenExpectResponse(EMPTY_RESERVATIONS);

    // reserve command
    whenCommand(reserveSeats(ROBY, SEAT_1_2, SEAT_1_3));

    // query reservations
    whenQuery(queryReservationsOf(ROBY));
    thenExpectResponse(responseReservations(reservationOf(SEAT_1_2, SEAT_1_3)));

    // reserve command
    whenCommand(reserveSeats(ROBY, SEAT_1_4));

    // query reservations
    whenQuery(queryReservationsOf(ROBY));
    thenExpectResponse(
        responseReservations(reservationOf(SEAT_1_2, SEAT_1_3), reservationOf(SEAT_1_4)));
  }
}
