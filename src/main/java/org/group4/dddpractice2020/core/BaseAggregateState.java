package org.group4.dddpractice2020.core;

import java.util.List;

public abstract class BaseAggregateState {

  public BaseAggregateState(List<DomainEvent> historyEvents) {
    init();

    for (DomainEvent event : historyEvents) {
      apply(event);
    }
  }

  protected abstract void init();

  protected abstract void apply(DomainEvent event);
}
