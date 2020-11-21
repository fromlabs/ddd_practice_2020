package org.group4.dddpractice2020.core;

import java.util.function.Consumer;

public abstract class BaseCommandHandler implements DomainCommandHandler {
  private final EventStore eventStore;
  private final Consumer<DomainEvent> publisher;

  public BaseCommandHandler(EventStore eventStore, Consumer<DomainEvent> publisher) {
    this.eventStore = eventStore;
    this.publisher =
        event -> {
          this.eventStore.add(event);

          publisher.accept(event);
        };
  }

  @Override
  public abstract void handle(DomainCommand command);

  protected Iterable<DomainEvent> getHistoryEvents() {
    return this.eventStore.getHistoryEvents();
  }

  protected Consumer<DomainEvent> getPublisher() {
    return this.publisher;
  }
}
