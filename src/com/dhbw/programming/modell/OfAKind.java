package com.dhbw.programming.modell;

public class OfAKind {
	private int number;
	private int points;
	private Boolean lock;

	public OfAKind(int number) {
		this.number = number;
		this.lock = false;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

}
