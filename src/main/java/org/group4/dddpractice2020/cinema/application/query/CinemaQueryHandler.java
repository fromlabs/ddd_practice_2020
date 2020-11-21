package org.group4.dddpractice2020.cinema.application.query;

import java.util.function.Consumer;
import org.group4.dddpractice2020.cinema.domain.readmodel.CustomerReservations;
import org.group4.dddpractice2020.core.BaseQueryHandler;
import org.group4.dddpractice2020.core.DomainQuery;
import org.group4.dddpractice2020.core.DomainQueryResponse;
import org.group4.dddpractice2020.core.EventStore;

public class CinemaQueryHandler extends BaseQueryHandler {
  public CinemaQueryHandler(EventStore eventStore, Consumer<DomainQueryResponse> responder) {
    super(eventStore, responder);
  }

  @Override
  public void handle(DomainQuery query) {
    if (query instanceof CustomerReservationQuery) {
      handleCustomerReservationQuery((CustomerReservationQuery) query);
    } else {
      throw new UnsupportedOperationException("Query: " + query);
    }
  }

  private void handleCustomerReservationQuery(CustomerReservationQuery query) {
    // TODO rtassi: the read model is created from scratch
    CustomerReservations model = new CustomerReservations(getHistoryEvents());

    respond(new CustomerReservationResponse(model.getCustomerReservations(query.getCustomerId())));
  }
}
