package org.group4.dddpractice2020.cinema.application.command;

import java.util.List;
import java.util.function.Consumer;
import org.group4.dddpractice2020.cinema.domain.aggregate.Screening;
import org.group4.dddpractice2020.cinema.domain.aggregate.ScreeningState;
import org.group4.dddpractice2020.core.BaseCommandHandler;
import org.group4.dddpractice2020.core.DomainCommand;
import org.group4.dddpractice2020.core.DomainEvent;

public class CommandHandler extends BaseCommandHandler {
  public CommandHandler(List<DomainEvent> historyEvents, Consumer<DomainEvent> publisher) {
    super(historyEvents, publisher);
  }

  @Override
  public void handle(DomainCommand command) {
    if (command instanceof ReserveCommand) {
      handleReserveCommand((ReserveCommand) command);
    } else {
      throw new UnsupportedOperationException("Command: " + command);
    }
  }

  private void handleReserveCommand(ReserveCommand command) {
    ScreeningState screeningState = new ScreeningState(getHistoryEvents());

    Screening screening = new Screening(screeningState, getPublisher());

    screening.reserveSeats(command.getCustomerId(), command.getReserveSeats());
  }
}
