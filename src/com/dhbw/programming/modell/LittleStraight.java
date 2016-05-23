package com.dhbw.programming.modell;

public class LittleStraight {
	private int points;
	private Boolean lock;

	public LittleStraight() {
		this.points = 30;
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
