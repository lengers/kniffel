package com.dhbw.programming.modell;

public class Kniffel {
	private int points;
	private Boolean lock;
	private Boolean show = false;
	private Boolean additionalKniffel = false;

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

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	public Boolean getAdditionalKniffel() {
		return additionalKniffel;
	}

	public void setAdditionalKniffel(Boolean additionalKniffel) {
		this.additionalKniffel = additionalKniffel;
	}

}
