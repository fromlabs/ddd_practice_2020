package org.group4.dddpractice2020.core;

public abstract class BaseReadModel {

  public BaseReadModel(Iterable<DomainEvent> historyEvents) {
    init();

    historyEvents.forEach(this::apply);
  }

  protected abstract void init();

  protected abstract void apply(DomainEvent event);
}
