package com.dhbw.programming.modell;

public class Throw {
	private int points;
	private int tmp;
	private Boolean lock;
	private Boolean show;

	public Throw(int tmp) {
		this.lock = false;
		this.show = false;
		this.tmp = tmp;
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
		if (show) {
			points = tmp;
		} else {
			tmp = points;
			points = 0;
		}
		this.show = show;
	}

}
