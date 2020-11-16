package org.group4.dddpractice2020.lab2;

import java.util.ArrayList;
import java.util.List;

public class EventStore {

	private final List<Object> events = new ArrayList<>();

	public void addEvents(List<Object> events) {
		this.events.addAll(events);
	}

	public List<Object> getEvents() {
		return this.events;
	}
}
