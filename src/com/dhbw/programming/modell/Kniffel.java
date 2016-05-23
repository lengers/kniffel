package com.dhbw.programming.modell;

public class Kniffel {
	private int points;
	private Boolean lock;

	public Kniffel() {
		this.points = 50;
		this.lock = false;
	}

	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
