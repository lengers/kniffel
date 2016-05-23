package com.dhbw.programming.modell;

public class FullHouse {
	private int points;
	private Boolean lock;

	public FullHouse() {
		this.points = 25;
		this.lock = false;
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
