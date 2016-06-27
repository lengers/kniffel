package com.dhbw.programming.modell;

public class Kniffel extends Throw {
	private Boolean additionalKniffel = false;

	public Kniffel() {
		super(50);
	}

	public Boolean getAdditionalKniffel() {
		return additionalKniffel;
	}

	public void setAdditionalKniffel(Boolean additionalKniffel) {
		this.additionalKniffel = additionalKniffel;
	}

}
