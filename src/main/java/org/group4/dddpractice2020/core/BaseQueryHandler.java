package org.group4.dddpractice2020.core;

import java.util.function.Consumer;

public abstract class BaseQueryHandler {
  private final EventStore eventStore;
  private final Consumer<DomainQueryResponse> responder;

  public BaseQueryHandler(EventStore eventStore, Consumer<DomainQueryResponse> responder) {
    this.eventStore = eventStore;
    this.responder = responder;
  }

  public abstract void handle(DomainQuery query);

  protected Iterable<DomainEvent> getHistoryEvents() {
    return this.eventStore.getHistoryEvents();
  }

  protected void respond(DomainQueryResponse response) {
    this.responder.accept(response);
  }
}
