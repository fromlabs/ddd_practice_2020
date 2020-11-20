package org.group4.dddpractice2020.core;

import java.util.List;
import java.util.function.Consumer;

public abstract class BaseQueryHandler {
  private final List<DomainEvent> historyEvents;
  private final Consumer<DomainQueryResponse> responder;

  public BaseQueryHandler(
      List<DomainEvent> historyEvents, Consumer<DomainQueryResponse> responder) {
    this.historyEvents = historyEvents;
    this.responder = responder;
  }

  public abstract void handle(DomainQuery query);

  protected List<DomainEvent> getHistoryEvents() {
    return this.historyEvents;
  }

  protected void respond(DomainQueryResponse response) {
    this.responder.accept(response);
  }
}
