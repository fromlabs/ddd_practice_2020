package org.group4.dddpractice2020.cinema.domain.valuetype;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Value;

@Value
public class ScreeningTime implements Comparable<ScreeningTime> {
  LocalDateTime dateTime;

  public ScreeningTime(LocalDate date, int hour, int minutes) {
    this.dateTime = LocalDateTime.of(date, LocalTime.of(hour, minutes));
  }

  @Override
  public int compareTo(ScreeningTime o) {
    return this.dateTime.compareTo(o.dateTime);
  }
}
