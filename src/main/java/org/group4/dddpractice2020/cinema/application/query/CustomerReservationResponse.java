package org.group4.dddpractice2020.cinema.application.query;

import java.util.List;
import lombok.Value;
import org.group4.dddpractice2020.cinema.domain.valuetype.Reservation;
import org.group4.dddpractice2020.core.DomainQueryResponse;

@Value
public class CustomerReservationResponse implements DomainQueryResponse {
  List<Reservation> seats;
}
