package org.group4.dddpractice2020.core;

import java.util.function.Consumer;

public abstract class BaseQueryHandler<R extends BaseReadModel> implements DomainQueryHandler {
  private final R readModel;
  private final EventStore eventStore;
  private final Consumer<DomainQueryResponse> responder;

  public BaseQueryHandler(
      R readModel, EventStore eventStore, Consumer<DomainQueryResponse> responder) {
    this.readModel = readModel;
    this.eventStore = eventStore;
    this.responder = responder;
  }

  protected R getReadModel() {
    return this.readModel;
  }

  protected void respond(DomainQueryResponse response) {
    this.responder.accept(response);
  }
}
