package org.group4.dddpractice2020.cinema.domain.valuetype;

import java.util.List;
import lombok.Value;

@Value
public class Reservation {
  List<Seat> seats;
}
