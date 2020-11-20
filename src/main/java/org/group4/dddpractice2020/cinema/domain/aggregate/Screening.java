package org.group4.dddpractice2020.cinema.domain.aggregate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import org.group4.dddpractice2020.cinema.domain.event.ReservationFailed;
import org.group4.dddpractice2020.cinema.domain.event.SeatsReserved;
import org.group4.dddpractice2020.cinema.domain.valuetype.Seat;
import org.group4.dddpractice2020.core.BaseAggregate;
import org.group4.dddpractice2020.core.DomainEvent;

public class Screening extends BaseAggregate<ScreeningState> {

  public Screening(ScreeningState aggregateState, Consumer<DomainEvent> publisher) {
    super(aggregateState, publisher);
  }

  public void reserveSeats(String customerId, List<Seat> requestedSeats) {
    LocalDateTime reservationTime = LocalDateTime.now();

    if (reservationTime.isAfter(
        getAggregateState().getScreeningTime().getDateTime().minusMinutes(15))) {
      apply(new ReservationFailed());
    } else if (getAggregateState().getAvailableSeats().containsAll(requestedSeats)) {
      apply(new SeatsReserved(customerId, requestedSeats));
    } else {
      apply(new ReservationFailed());
    }
  }
}
