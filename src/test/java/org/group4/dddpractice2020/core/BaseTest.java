package org.group4.dddpractice2020.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

  private EventStore eventStore;
  private List<DomainEvent> publishedEvents;
  private DomainQueryResponse queryResponse;

  @BeforeEach
  void setup() {
    this.eventStore = new EventStoreImpl();
    this.publishedEvents = new ArrayList<>();
    this.queryResponse = null;
  }

  protected BaseCommandHandler commandHandlerFactory(
      EventStore eventStore, Consumer<DomainEvent> publisher) {
    throw new UnsupportedOperationException("Provide a CommandHandler factory");
  }

  protected BaseQueryHandler queryHandlerFactory(
      EventStore eventStore, Consumer<DomainQueryResponse> responder) {
    throw new UnsupportedOperationException("Provide a QueryHandler factory");
  }

  protected void given(DomainEvent... events) {
    this.eventStore = new EventStoreImpl(events);
  }

  protected void whenCommand(DomainCommand command) {
    BaseCommandHandler handler =
        commandHandlerFactory(
            this.eventStore,
            (event) -> {
              this.publishedEvents.add(event);
            });

    handler.handle(command);
  }

  protected void whenQuery(DomainQuery query) {
    BaseQueryHandler handler =
        queryHandlerFactory(
            this.eventStore,
            (response) -> {
              this.queryResponse = response;
            });

    handler.handle(query);
  }

  protected void thenExpectEvents(DomainEvent... events) {
    Assertions.assertTrue(
        this.publishedEvents.size() == events.length
            && this.publishedEvents.containsAll(Arrays.asList(events)));
  }

  protected void thenExpectResponse(DomainQueryResponse response) {
    Assertions.assertEquals(response, this.queryResponse);
  }
}
