package com.dhbw.programming.modell;

public class Throw {
	private int points;
	private int tmp;
	private Boolean lock;
	private Boolean show;

	public Throw(int tmp) {
		this.lock = false;
		this.show = false;
		this.points = tmp;
		this.tmp = tmp;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
		this.tmp = points;
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
		if (lock == false) {
			if (show == true && points < 1) {
				points = tmp;
			} else if (show) {
				points = tmp;
			} else {
				points = 0;
			}
		} else {
			points = tmp;
		}

		this.show = show;
	}

}
