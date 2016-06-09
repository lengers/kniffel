package com.dhbw.programming.modell;

public class Chance {
	private int points;
	private Boolean lock;
	private Boolean show = false;

	public Chance() {
		this.points = 0;
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

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

}
