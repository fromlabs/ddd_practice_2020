package org.group4.dddpractice2020.cinema.application.query;

import lombok.Value;
import org.group4.dddpractice2020.core.DomainQuery;

@Value
public class CustomerReservationQuery implements DomainQuery {
  String customerId;
}
