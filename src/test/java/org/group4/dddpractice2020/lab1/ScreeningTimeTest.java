package org.group4.dddpractice2020.lab1;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
class ScreeningTimeTest {

	@Test
	void getDateTime() {
		log.info("Just info log");

		ScreeningTime screeningTime = new ScreeningTime(LocalDate.of(2020, Month.JANUARY, 1), 10, 30);

		assertThat(screeningTime.getDateTime().getHour()).isEqualTo(10);
	}
}