package org.group4.dddpractice2020.cinema.application.command;

import java.util.List;
import lombok.Value;
import org.group4.dddpractice2020.cinema.domain.valuetype.Seat;
import org.group4.dddpractice2020.core.DomainCommand;

@Value
public class ReserveCommand implements DomainCommand {
  String customerId;
  List<Seat> reserveSeats;
}
