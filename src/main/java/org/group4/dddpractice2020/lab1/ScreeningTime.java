package org.group4.dddpractice2020.lab1;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@EqualsAndHashCode
public class ScreeningTime implements Comparable<ScreeningTime> {
	private final LocalDateTime dateTime;

	// TODO rtassi: hour and minutes in a ValueType?
	public ScreeningTime(LocalDate date, int hour, int minutes) {
		// TODO rtassi: assertions

		this.dateTime = LocalDateTime.of(date, LocalTime.of(hour, minutes));
	}

	public LocalDateTime getDateTime() {
		return this.dateTime;
	}

	// TODO rtassi: other utility functions

	@Override
	public int compareTo(ScreeningTime o) {
		return this.dateTime.compareTo(o.dateTime);
	}
}
