package org.group4.dddpractice2020.core;

public interface EventStore {

  Iterable<DomainEvent> getHistoryEvents();

  void add(DomainEvent event);

  void addAll(Iterable<DomainEvent> events);
}
