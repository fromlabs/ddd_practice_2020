package org.group4.dddpractice2020.core;

public interface EventStore {

  // TODO rtassi: an observable is better
  Iterable<DomainEvent> getHistoryEvents();

  void add(DomainEvent event);

  void addAll(Iterable<DomainEvent> events);
}
