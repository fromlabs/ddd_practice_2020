package org.group4.dddpractice2020.core;

import java.util.List;
import java.util.function.Consumer;

public abstract class BaseCommandHandler {
  private final List<DomainEvent> historyEvents;
  private final Consumer<DomainEvent> publisher;

  public BaseCommandHandler(List<DomainEvent> historyEvents, Consumer<DomainEvent> publisher) {
    this.historyEvents = historyEvents;
    this.publisher =
        event -> {
          historyEvents.add(event);

          publisher.accept(event);
        };
  }

  public abstract void handle(DomainCommand command);

  protected List<DomainEvent> getHistoryEvents() {
    return this.historyEvents;
  }

  protected Consumer<DomainEvent> getPublisher() {
    return this.publisher;
  }
}
