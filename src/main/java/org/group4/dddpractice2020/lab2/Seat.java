package org.group4.dddpractice2020.lab2;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Seat {
	private final int row;
	private final int col;

	public Seat(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}
}
