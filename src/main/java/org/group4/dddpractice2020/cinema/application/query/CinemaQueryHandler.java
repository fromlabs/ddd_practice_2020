package org.group4.dddpractice2020.cinema.application.query;

import java.util.function.Consumer;
import org.group4.dddpractice2020.cinema.domain.readmodel.CustomerReservations;
import org.group4.dddpractice2020.core.BaseQueryHandler;
import org.group4.dddpractice2020.core.DomainQuery;
import org.group4.dddpractice2020.core.DomainQueryResponse;
import org.group4.dddpractice2020.core.EventStore;

public class CinemaQueryHandler extends BaseQueryHandler<CustomerReservations> {
  public CinemaQueryHandler(
      CustomerReservations readModel,
      EventStore eventStore,
      Consumer<DomainQueryResponse> responder) {
    super(readModel, eventStore, responder);
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
    respond(
        new CustomerReservationResponse(
            getReadModel().getCustomerReservations(query.getCustomerId())));
  }
}
