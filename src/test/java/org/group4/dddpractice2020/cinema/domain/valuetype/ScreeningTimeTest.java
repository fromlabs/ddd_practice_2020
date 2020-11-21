package org.group4.dddpractice2020.cinema.domain.valuetype;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
class ScreeningTimeTest {

  @Test
  void getDateTime() {
    ScreeningTime screeningTime = new ScreeningTime(LocalDate.of(2020, Month.JANUARY, 1), 10, 30);

    assertThat(screeningTime.getDateTime().getHour()).isEqualTo(10);
  }
}
