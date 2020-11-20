package org.group4.dddpractice2020.cinema.domain.event;

import java.util.List;
import lombok.Value;
import org.group4.dddpractice2020.cinema.domain.valuetype.ScreeningTime;
import org.group4.dddpractice2020.cinema.domain.valuetype.Seat;
import org.group4.dddpractice2020.core.DomainEvent;

@Value
public class ScreeningCreated implements DomainEvent {
  ScreeningTime screeningTime;
  List<Seat> seats;
}
