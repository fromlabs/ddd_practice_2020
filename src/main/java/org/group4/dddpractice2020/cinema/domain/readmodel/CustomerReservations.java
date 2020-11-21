package org.group4.dddpractice2020.cinema.domain.readmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.group4.dddpractice2020.cinema.domain.event.SeatsReserved;
import org.group4.dddpractice2020.cinema.domain.valuetype.Reservation;
import org.group4.dddpractice2020.core.BaseReadModel;
import org.group4.dddpractice2020.core.DomainEvent;

public class CustomerReservations extends BaseReadModel {
  private Map<String, List<Reservation>> reservations;

  public CustomerReservations(Iterable<DomainEvent> historyEvents) {
    super(historyEvents);
  }

  @Override
  protected void init() {
    this.reservations = new HashMap<>();
  }

  public List<Reservation> getCustomerReservations(String customerId) {
    return this.reservations.getOrDefault(customerId, Collections.emptyList());
  }

  @Override
  protected void apply(DomainEvent event) {
    if (event instanceof SeatsReserved) {
      applySeatsReserved((SeatsReserved) event);
    }
  }

  private void applySeatsReserved(SeatsReserved event) {
    List<Reservation> customerReservations =
        this.reservations.computeIfAbsent(event.getCustomerId(), id -> new ArrayList<>());

    customerReservations.add(new Reservation(event.getSeats()));
  }
}
