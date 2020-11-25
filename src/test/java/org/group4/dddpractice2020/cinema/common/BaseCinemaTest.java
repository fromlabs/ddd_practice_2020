package org.group4.dddpractice2020.cinema.common;

import com.google.common.collect.Lists;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;
import org.group4.dddpractice2020.cinema.application.command.CinemaCommandHandler;
import org.group4.dddpractice2020.cinema.application.command.ReserveCommand;
import org.group4.dddpractice2020.cinema.application.query.CinemaQueryHandler;
import org.group4.dddpractice2020.cinema.application.query.CustomerReservationQuery;
import org.group4.dddpractice2020.cinema.application.query.CustomerReservationResponse;
import org.group4.dddpractice2020.cinema.domain.event.ReservationFailed;
import org.group4.dddpractice2020.cinema.domain.event.ScreeningCreated;
import org.group4.dddpractice2020.cinema.domain.event.SeatsReserved;
import org.group4.dddpractice2020.cinema.domain.readmodel.CustomerReservations;
import org.group4.dddpractice2020.cinema.domain.valuetype.Reservation;
import org.group4.dddpractice2020.cinema.domain.valuetype.ScreeningTime;
import org.group4.dddpractice2020.cinema.domain.valuetype.Seat;
import org.group4.dddpractice2020.core.BaseTest;
import org.group4.dddpractice2020.core.DomainCommandHandler;
import org.group4.dddpractice2020.core.DomainEvent;
import org.group4.dddpractice2020.core.DomainQueryHandler;
import org.group4.dddpractice2020.core.DomainQueryResponse;
import org.group4.dddpractice2020.core.EventStore;

public abstract class BaseCinemaTest extends BaseTest<CustomerReservations> {

  public static final String ROBY = "roby";
  public static final String RONY = "rony";

  public static final Seat SEAT_1_1 = new Seat(1, 1);
  public static final Seat SEAT_1_2 = new Seat(1, 2);
  public static final Seat SEAT_1_3 = new Seat(1, 3);
  public static final Seat SEAT_1_4 = new Seat(1, 4);

  public static final ScreeningTime SCREENING_TIME_IN_100 = screeningTimeIn(100);
  public static final ScreeningTime SCREENING_TIME_IN_10 = screeningTimeIn(10);

  public static final ReservationFailed RESERVATION_FAILED = new ReservationFailed();

  public static ScreeningTime screeningTimeIn(int minutes) {
    LocalDateTime screeningDateTime = LocalDateTime.now().plusMinutes(minutes);

    return new ScreeningTime(
        screeningDateTime.toLocalDate(),
        screeningDateTime.getHour(),
        screeningDateTime.getMinute());
  }

  public static ScreeningCreated screeningCreated(ScreeningTime screeningTime) {
    return new ScreeningCreated(
        screeningTime, Arrays.asList(SEAT_1_1, SEAT_1_2, SEAT_1_3, SEAT_1_4));
  }

  public static CustomerReservationQuery queryReservationsOf(String customerId) {
    return new CustomerReservationQuery(customerId);
  }

  public static CustomerReservationResponse responseReservationsOf(Seat... reservedSeats) {
    return new CustomerReservationResponse(
        Collections.singletonList(new Reservation(Lists.newArrayList(reservedSeats))));
  }

  public static ReserveCommand reserveSeats(String customerId, Seat... seats) {
    return new ReserveCommand(customerId, Lists.newArrayList(seats));
  }

  public static SeatsReserved seatsReserved(String customerId, Seat... seats) {
    return new SeatsReserved(customerId, Lists.newArrayList(seats));
  }

  public static final CustomerReservationResponse EMPTY_RESERVATIONS =
      new CustomerReservationResponse(Collections.emptyList());

  @Override
  protected DomainCommandHandler commandHandlerFactory(
      EventStore eventStore, Consumer<DomainEvent> publisher) {
    return new CinemaCommandHandler(eventStore, publisher);
  }

  @Override
  protected CustomerReservations createReadModel(Iterable<DomainEvent> events) {
    return new CustomerReservations(events);
  }

  @Override
  protected DomainQueryHandler queryHandlerFactory(
      CustomerReservations readModel,
      EventStore eventStore,
      Consumer<DomainQueryResponse> responder) {
    return new CinemaQueryHandler(readModel, eventStore, responder);
  }
}
