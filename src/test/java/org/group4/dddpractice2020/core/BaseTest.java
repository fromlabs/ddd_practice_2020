package org.group4.dddpractice2020.core;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest<M extends BaseReadModel> {

  private EventStore eventStore;
  private List<DomainEvent> publishedEvents;
  private DomainQueryResponse queryResponse;
  private M readModel;

  @BeforeEach
  void setup() {
    this.eventStore = new EventStoreImpl();
    this.publishedEvents = new ArrayList<>();
    this.queryResponse = null;
  }

  protected DomainCommandHandler commandHandlerFactory(
      EventStore eventStore, Consumer<DomainEvent> publisher) {
    throw new UnsupportedOperationException("Provide a CommandHandler factory");
  }

  protected M createReadModel(Iterable<DomainEvent> events) {
    throw new UnsupportedOperationException("Provide a ReadModel factory");
  }

  protected DomainQueryHandler queryHandlerFactory(
      M readModel, EventStore eventStore, Consumer<DomainQueryResponse> responder) {
    throw new UnsupportedOperationException("Provide a QueryHandler factory");
  }

  protected void given(DomainEvent... events) {
    this.eventStore = new EventStoreImpl(events);
    this.readModel = createReadModel(Lists.newArrayList(events));
  }

  protected void whenCommand(DomainCommand command) {
    DomainCommandHandler handler =
        commandHandlerFactory(
            this.eventStore,
            (event) -> {
              this.publishedEvents.add(event);
              this.readModel.apply(event);
            });

    handler.handle(command);
  }

  protected void whenQuery(DomainQuery query) {
    DomainQueryHandler handler =
        queryHandlerFactory(
            this.readModel,
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
