package org.group4.dddpractice2020.cinema.domain.aggregate;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import org.group4.dddpractice2020.cinema.domain.event.ReservationFailed;
import org.group4.dddpractice2020.cinema.domain.event.ScreeningCreated;
import org.group4.dddpractice2020.cinema.domain.event.SeatsReserved;
import org.group4.dddpractice2020.cinema.domain.valuetype.ScreeningTime;
import org.group4.dddpractice2020.cinema.domain.valuetype.Seat;
import org.group4.dddpractice2020.core.BaseAggregateState;
import org.group4.dddpractice2020.core.DomainEvent;

public class ScreeningState extends BaseAggregateState {
  private Map<Seat, String> seats;
  private ScreeningTime screeningTime;

  public ScreeningState(Iterable<DomainEvent> historyEvents) {
    super(historyEvents);
  }

  @Override
  protected void init() {
    this.seats = new HashMap<>();
  }

  public ScreeningTime getScreeningTime() {
    return this.screeningTime;
  }

  public Set<Seat> getAvailableSeats() {
    return this.seats.entrySet().stream()
        .filter(seatEntry -> seatEntry.getValue() == null)
        .map(Entry::getKey)
        .collect(Collectors.toSet());
  }

  @Override
  protected void apply(DomainEvent event) {
    if (event instanceof ScreeningCreated) {
      applyScreeningCreated((ScreeningCreated) event);
    } else if (event instanceof SeatsReserved) {
      applySeatsReserved((SeatsReserved) event);
    } else if (event instanceof ReservationFailed) {
      applyReservationFailed((ReservationFailed) event);
    } else {
      throw new UnsupportedOperationException("Event: " + event);
    }
  }

  private void applyScreeningCreated(ScreeningCreated event) {
    this.screeningTime = event.getScreeningTime();
    for (Seat seat : event.getSeats()) {
      this.seats.put(seat, null);
    }
  }

  private void applySeatsReserved(SeatsReserved event) {
    for (Seat seat : event.getSeats()) {
      this.seats.put(seat, event.getCustomerId());
    }
  }

  private void applyReservationFailed(ReservationFailed event) {
    //
  }
}
