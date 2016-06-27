package com.dhbw.programming.modell;

public class OfAKind extends Throw {
	private int number;

	public OfAKind(int number) {
		super(-1);
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
