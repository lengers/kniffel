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
		if (!lock) {
			if (show == true && points < 1) {
				points = tmp;
				System.out.println("reusing default points for " + this.getClass().getName());
			} else if (show) {
				System.out.println("using default points for " + this.getClass().getName());
				points = tmp;
			} else {
				System.out.println("setting points to 0 for " + this.getClass().getName());
				points = 0;
			}
		} else {
			points = tmp;
			System.out.println("Setting points to " + points + " for " + this.getClass().getName());
		}

		this.show = show;
	}

}
