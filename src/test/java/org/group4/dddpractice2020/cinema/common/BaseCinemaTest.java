package org.group4.dddpractice2020.cinema.common;

import java.util.function.Consumer;
import org.group4.dddpractice2020.cinema.application.command.CinemaCommandHandler;
import org.group4.dddpractice2020.cinema.application.query.CinemaQueryHandler;
import org.group4.dddpractice2020.cinema.domain.readmodel.CustomerReservations;
import org.group4.dddpractice2020.core.BaseTest;
import org.group4.dddpractice2020.core.DomainCommandHandler;
import org.group4.dddpractice2020.core.DomainEvent;
import org.group4.dddpractice2020.core.DomainQueryHandler;
import org.group4.dddpractice2020.core.DomainQueryResponse;
import org.group4.dddpractice2020.core.EventStore;

public abstract class BaseCinemaTest extends BaseTest<CustomerReservations> {

  @Override
  protected DomainCommandHandler commandHandlerFactory(
      EventStore eventStore, Consumer<DomainEvent> publisher) {
    return new CinemaCommandHandler(eventStore, publisher);
  }

  @Override
  protected CustomerReservations createReadModel(Iterable<DomainEvent> events) {
    return new CustomerReservations(events);
  }

  @Override
  protected DomainQueryHandler queryHandlerFactory(
      CustomerReservations readModel,
      EventStore eventStore,
      Consumer<DomainQueryResponse> responder) {
    return new CinemaQueryHandler(readModel, eventStore, responder);
  }
}
