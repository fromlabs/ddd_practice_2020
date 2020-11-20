package org.group4.dddpractice2020.cinema.application.query;

import java.util.List;
import java.util.function.Consumer;
import org.group4.dddpractice2020.cinema.domain.readmodel.CustomerReservations;
import org.group4.dddpractice2020.core.BaseQueryHandler;
import org.group4.dddpractice2020.core.DomainEvent;
import org.group4.dddpractice2020.core.DomainQuery;
import org.group4.dddpractice2020.core.DomainQueryResponse;

public class QueryHandler extends BaseQueryHandler {
  public QueryHandler(List<DomainEvent> historyEvents, Consumer<DomainQueryResponse> responder) {
    super(historyEvents, responder);
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
    CustomerReservations model = new CustomerReservations(getHistoryEvents());

    respond(new CustomerReservationResponse(model.getCustomerReservations(query.getCustomerId())));
  }
}
