package com.dhbw.programming.modell;

public class BigStraight {
	private int points;
	private Boolean lock;

	public BigStraight() {
		this.points = 40;
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
