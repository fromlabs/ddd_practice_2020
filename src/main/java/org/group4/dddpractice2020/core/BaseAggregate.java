package org.group4.dddpractice2020.core;

import java.util.function.Consumer;

public class BaseAggregate<AS extends BaseAggregateState> {
  private final AS aggregateState;
  private final Consumer<DomainEvent> publisher;

  public BaseAggregate(AS aggregateState, Consumer<DomainEvent> publisher) {
    this.aggregateState = aggregateState;
    this.publisher = publisher;
  }

  protected AS getAggregateState() {
    return this.aggregateState;
  }

  protected void apply(DomainEvent event) {
    this.aggregateState.apply(event);

    this.publisher.accept(event);
  }
}
