package org.group4.dddpractice2020.core;

import com.google.common.collect.Lists;
import java.util.List;

public class EventStoreImpl implements EventStore {

  private final List<DomainEvent> historyEvents;

  public EventStoreImpl(DomainEvent... historyEvents) {
    this.historyEvents = Lists.newArrayList(historyEvents);
  }

  public EventStoreImpl(Iterable<DomainEvent> historyEvents) {
    this.historyEvents = Lists.newArrayList(historyEvents);
  }

  @Override
  public void add(DomainEvent event) {
    this.historyEvents.add(event);
  }

  @Override
  public void addAll(Iterable<DomainEvent> events) {
    events.forEach(this::add);
  }

  @Override
  public Iterable<DomainEvent> getHistoryEvents() {
    return this.historyEvents;
  }
}
